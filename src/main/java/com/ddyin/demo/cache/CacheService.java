package com.ddyin.demo.cache;

import com.alibaba.fastjson.JSONObject;
import com.ddyin.demo.dao.model.UserInfo;
import com.ddyin.demo.service.UserInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ddyin
 * @Description
 * @date 2018/7/13 10:32
 */
@Service
public class CacheService {

    public final static Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    //缓存容量
    public static final int CACHE_CAPACITY=1000;

    public static LinkedList<String> hLinkedList = new LinkedList<String>();

    @Autowired
    private UserInfoService userInfoService;

    public void flushMemoryCache(){
        try {
            List<UserInfo> specialofferproducts = userInfoService.queryAllInfo();
            if(CollectionUtils.isNotEmpty(specialofferproducts)){
                hLinkedList.clear();
                specialofferproducts.stream().forEach( sop ->{
                    if(hLinkedList.size() < CACHE_CAPACITY){
                        hLinkedList.addLast(JSONObject.toJSONString(sop));
                    }else {
                        logger.info("缓存容量已满");
                    }
                });
            }
            logger.info("CacheService.flushMemoryCache finish");
        } catch (Exception e) {
            logger.error("CacheService.flushMemoryCache execption:"+e);
        }
    }
}
