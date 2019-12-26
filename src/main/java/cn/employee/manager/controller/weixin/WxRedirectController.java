package cn.employee.manager.controller.weixin;

import cn.employee.manager.dto.LoginDTO;
import cn.employee.manager.entity.Employee;
import cn.employee.manager.mapper.EmployeeMapper;
import cn.employee.manager.socket.WebSocketServer;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zfitness
 */
@AllArgsConstructor
@RestController
@RequestMapping("/wx/redirect/{appid}")
public class WxRedirectController {
    private final WxMpService wxService;
    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping("/greet")
    public WxMpUser greetUser(@PathVariable String appid, @RequestParam String code, ModelMap map) {
        if (!this.wxService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        WxMpUser user = null;
        try {
            WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
            user = wxService.oauth2getUserInfo(accessToken, null);
            map.put("user", user);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        //websocket 开启登录功能
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getOpenid, user.getOpenId());
        Employee employee = employeeMapper.selectOne(wrapper);
        if (employee != null) {
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setUserId(employee.getId());
            loginDTO.setToken(UUID.randomUUID().toString());
            if (employee.getAuthority() == 1) {
                loginDTO.setSuper(true);
            } else {
                loginDTO.setSuper(false);
            }
            //发送消息给客户端
            try {
                WebSocketServer.sendInfo(loginDTO, "login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                WebSocketServer.sendInfo("用户不存在", "error");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return user;
    }
}
