package com.ddyin.demo.controller;

import com.ddyin.demo.common.CommonConstant;
import com.ddyin.demo.dao.model.UserInfo;
import com.ddyin.demo.service.UserInfoService;
import com.ddyin.demo.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ddyin
 * @Description
 * @date 2018/9/12 18:30
 */
@RestController
@RequestMapping("/userinfo")
public class UserInfoController extends BaseController{

    public final static Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 全量查询
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public ModelMap selectAll() {
        try {
            List<UserInfo> list=userInfoService.queryAllInfo();
            return retResult(CommonConstant.SUCCESS_CODE,CommonConstant.SUCCESS_MSG,list);
        } catch (Exception e) {
            logger.error("UserInfoController.selectAll:" + e.getMessage());
            return retResult(CommonConstant.FAIL_CODE,CommonConstant.FAIL_MSG,null);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelMap addUserinfo(@RequestBody UserInfo userInfo, @RequestHeader(name = "email") String email) {
        try {
            UserInfo userInfo1 = getUserInfo(request);
            userInfo.setUid(userInfo1.getUid());
            userInfoService.saveUserInfo(userInfo);
            return retResult(CommonConstant.SUCCESS_CODE, CommonConstant.SUCCESS_MSG, null);
        } catch (Exception e) {
            LoggerUtil.error("UserInfoController addUserinfo exception : " + e.getMessage(), e);
            return retResult(CommonConstant.FAIL_CODE, e.getMessage(), null);
        }
    }

}
