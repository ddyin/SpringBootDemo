package com.ddyin.demo.service;

import com.ddyin.demo.dao.mapper.UserInfoMapper;
import com.ddyin.demo.dao.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ddyin
 * @Description
 * @date 2018/9/12 18:24
 */
@Service
public class UserInfoService {

    public final static Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    public Integer saveUserInfo(UserInfo userInfo){
        try {
            return userInfoMapper.insert(userInfo);
        } catch (Exception e) {
            logger.error("UserInfoService saveUserInfo execption :",e);
            return null;
        }
    }


    public List<UserInfo> queryAllInfo(){
        try {
            return userInfoMapper.selectAll();
        } catch (Exception e) {
            logger.error("UserInfoService queryAllInfo execption :",e);
            return null;
        }
    }

}
