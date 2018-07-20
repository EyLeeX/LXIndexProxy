package mfw.index.client.test;

import cmc.index.client.IndexerClient;
import cmc.index.client.test.pojo.User;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class IndexerClientTest {
	
	
	
	/**
	 * 测试添加单个索引
	 */
	@Test
	public void testCreate(){
		User user =new User();
		user.setEmail("mtime@qq.com");
		user.setId(12345637l);
		user.setMobile("18310667310");
		user.setNickname("校长");
		user.setUsername("zhangsna");
		user.setBirthday(LocalDateTime.now());
		
		try {
			IndexerClient.createIndex(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 测试添加索引列表
	 */
	@Test
	public void testCreateList(){
		List<User> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user =new User();
			user.setBirthday(LocalDateTime.now());
			user.setEmail("mtime@qqq.com"+i);
			user.setId(123456477l+i);
			user.setMobile("183106627310"+i);
			user.setNickname("校长en"+i);
			user.setUsername("zhang22sna"+i);
			list.add(user);
		}
		try {
			IndexerClient.createIndex(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试更新单条索引
	 */
	@Test
	public void testUpdate(){
		User user =new User();
		user.setBirthday(LocalDateTime.now());
		user.setEmail("mtime@qq.com10");
		user.setId(123456477l);
		user.setMobile("18310667310");
		user.setNickname("校长10");
		user.setUsername("zhangsna10");
		try {
			IndexerClient.updateIndex(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 测试批量更新索引
	 */
	@Test
	public void testUpdateList(){
		List<User> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user =new User();
			user.setEmail("mtime@qq.com"+i+"i");
			user.setId(123456477l+i);
			user.setMobile("18310667310"+i+"i");
			user.setNickname("校长"+i);
			user.setUsername("zhangsna"+i+"i");
			list.add(user);
		}
		try {
			IndexerClient.updateIndex(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 测试删除单条索引
	 */
	@Test
	public void testDelete(){
		
		try {
			IndexerClient.deleteIndex(User.class, "12345647");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 测试批量删除索引
	 */
	@Test
	public void testDeleteList(){
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(String.valueOf(12345647+i));
		}
		try {
			IndexerClient.deleteIndex(User.class, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void test(){
	}
	
}
