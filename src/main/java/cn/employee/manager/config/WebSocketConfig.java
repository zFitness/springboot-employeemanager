package cn.employee.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * springboot websocket 配置类
 * @author zfitness
 */
@Component
public class WebSocketConfig {
    /**
     * 当使用war包部署时， 这里需要注释掉
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
