package mfw.index.search.service;

import mtime.lark.net.rpc.RpcApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 封装服务启动引导功能。
 */
//@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@Configuration
@EnableAutoConfiguration
@ComponentScan("cmc.index")
public class SearchServiceBootstrap {
    /**
     * 启动函数
     * @param args
     */
    public static void main(String[] args) {
        RpcApplication app = new RpcApplication(SearchServiceBootstrap.class, args);
        app.run();
    }
}