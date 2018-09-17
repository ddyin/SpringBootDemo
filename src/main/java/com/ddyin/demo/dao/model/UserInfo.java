package com.ddyin.demo.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_info")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {
    /**
     * id主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "Uid")
    private String uid;

    /**
     * 用户信息(email,tel,guestname,phonetype)
     */
    @Column(name = "Content")
    private String content;

    /**
     * 创建时间(落库时间)
     */
    @Column(name = "CreateTime")
    private Date createtime;

    /**
     * 扩展数据
     */
    @Column(name = "ExtData")
    private String extdata;
}