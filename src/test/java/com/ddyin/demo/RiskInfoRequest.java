package com.ddyin.demo;

import lombok.Data;

/**
 * @author ddyin
 * @Description
 * @date 2018/9/17 11:44
 */
@Data
public class RiskInfoRequest {

    //场景
    private String sceneType;
    //类型
    private String infoType;
    //值
    private String infoValue;
    //数据状态("0":Normal "1":Expired "2":Removed)
    private String infoStatus;

}
