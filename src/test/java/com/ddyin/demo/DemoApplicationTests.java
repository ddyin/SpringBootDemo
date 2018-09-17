package com.ddyin.demo;

import com.ddyin.demo.dao.mapper.UserInfoMapper;
import com.ddyin.demo.dao.model.UserInfo;
import com.ddyin.demo.service.UserInfoService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

//@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private UserInfoService userInfoService;

	@Test
	public void contextLoads() {
		UserInfo userInfo = new UserInfo();
		userInfo.setContent("1177199");
		userInfo.setCreatetime(new Date());
		userInfo.setExtdata("1177199");
		userInfo.setUid("1177199");

		int insert = userInfoMapper.insert(userInfo);
		System.out.println(insert);
	}

	@Test
	public void testSelect(){
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1L);
		System.out.println(userInfo);
	}

	@Test
	public void testSelectAll(){
		List<UserInfo> userInfos = userInfoService.queryAllInfo();
		System.out.println(userInfos.toString());
	}


	public static void main(String[] args) {

		LoadingCache<String, String> build = CacheBuilder.newBuilder().initialCapacity(1).maximumSize(100)
				.expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, String>() {
			@Override
			public String load(String key) {
				return "";
			}
		});
		System.out.println(build.asMap().toString());
	}



}
