package mfw.index.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
public class SearchBootStrap {
	/**
	 * 启动函数
	 * @param args
	 */
	public static void main(String[] args) {
		new SpringApplication(SearchBootStrap.class, args).run();
	}

}
