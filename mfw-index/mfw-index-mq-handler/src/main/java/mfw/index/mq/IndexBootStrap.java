package mfw.index.mq;

import mtime.lark.util.msg.MsgApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 启动类
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("cmc.index")
public class IndexBootStrap {

	/**
	 * 启动函数
	 * @param args
     */
	public static void main(String[] args) {
		MsgApplication app = new MsgApplication(IndexBootStrap.class, args);
        app.run();
	}

}
