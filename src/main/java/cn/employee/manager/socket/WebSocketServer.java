package cn.employee.manager.socket;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket 服务端
 * 注意： websocket 不能被代理，还有下面几个注解修饰的方法必须是public的
 *
 * @author zfitness
 */

@Component
@ServerEndpoint("/websocket/login")
@Slf4j
public class WebSocketServer {
//    static Log log = LogFactory.get(WebSocketServer.class);
    /**
     * 记录连接数量
     */
    private static int onlineCount = 0;
    /**
     * juc中的线程安全容器
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    /**
     * 存放websocket 中的会话
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        log.info("新增一个websocket连接，现在连接数" + getOnlineCount());
    }

    /**
     * websocket 连接断开调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        log.info("断开websocket一个连接，现在连接数:" + getOnlineCount());
    }

    /**
     * 收到消息调用此方法
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("websocket 收到消息了: " + message);
        try {
            sendInfo("hello, too!!!", "text");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误调用的方法
     *
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("发送错误,  ");
        throwable.printStackTrace();
    }

    /**
     * 实现主动推送消息到客户端
     */
    public void sendMessage(String message) throws IOException {
        if (session != null) {
            this.session.getBasicRemote().sendText(message);
        } else {
            log.info("session为空");
        }
    }

    public static void sendInfo(Object message, String type) throws IOException {
        log.info("推送消息到窗口，推送内容:" + message);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("type", type);
        resultMap.put("message", message);
        JSONObject jsonObject = JSONUtil.parseObj(resultMap);
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                item.sendMessage(jsonObject.toString());
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
