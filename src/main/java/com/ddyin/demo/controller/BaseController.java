package com.ddyin.demo.controller;

import com.ddyin.demo.dao.model.UserInfo;
import com.ddyin.demo.util.LoggerUtil;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @author ddyin
 * @Description
 * @date 2018/9/12 18:30
 */
public class BaseController {



    public ModelMap retResult(String code, String msg, Object content){
        ModelMap modelMap = new ModelMap();
        modelMap.put("code", code);
        modelMap.put("msg", msg);
        modelMap.put("result", content);
        return modelMap;
    }

    public ModelMap retResult(boolean flag, String msg, Object content){
        ModelMap modelMap = new ModelMap();
        modelMap.put("status", flag);
        modelMap.put("msg", msg);
        modelMap.put("result", content);
        return modelMap;
    }

    //从header中获取信息
    public UserInfo getUserInfo(HttpServletRequest request){
        UserInfo user = new UserInfo();
        try {
            if(!StringUtils.isEmpty(request.getHeader("uid"))){
                user.setUid(request.getHeader("uid"));
            }else{
                LoggerUtil.info("getUserInfo uid is null ###");
            }
            if(!StringUtils.isEmpty(request.getHeader("content"))){
                user.setContent(URLDecoder.decode(request.getHeader("content")));
            }else{
                LoggerUtil.info("getUserInfo content is null ###");
            }
        } catch (Exception e) {
            LoggerUtil.error("getUserInfo Exception: ", e);
        }
        return user;
    }

}
