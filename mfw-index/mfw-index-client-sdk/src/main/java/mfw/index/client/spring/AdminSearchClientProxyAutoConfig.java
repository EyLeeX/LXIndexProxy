package mfw.index.client.spring;

import mfw.index.client.SearcherClient;
import mfw.index.client.SearcherLogClient;


/**
 * 提供 Spring 自动注入默认服务代理功能。
 */
// TODO: 2018/7/20  duboo  实现
public class AdminSearchClientProxyAutoConfig {
	
	
	/**
	 * 自动注入
	 * @return
	 */
	public SearcherClient searcherClientProxy(){
		return new SearcherClient();
	}

	/**
	 * 自动注入
	 * @return
	 */
	public SearcherLogClient searcherLogClientProxy(){
		return new SearcherLogClient();
	}

}
