package com.forum.modules.user.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.forum.common.base.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author zhaohuan
 * @since 2020-05-03
 */
@Data
@TableName("user")
@EqualsAndHashCode(callSuper = true)
public class UserDO extends BaseDO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * MD5密码
     */
    private String password;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 所在行业
     */
    private String industry;

    /**
     * 职业经历
     */
    private String career;

    /**
     * 个人简介
     */
    private String education;

    /**
     * 教育经历
     */
    private String introduction;

    /**
     * 星标量
     */
    private Integer starScalar;

    /**
     * 关联三方表—关注表 user_id
     */
    private Integer followId;

    /**
     * 角色
     */
    private Integer roleId;

    /**
     * 关联三方表—文章表
     */
    private Integer articleId;

    /**
     *  备注
     */
    private String remark;
}
