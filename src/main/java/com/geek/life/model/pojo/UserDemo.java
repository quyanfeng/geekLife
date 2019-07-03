package com.geek.life.model.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UserDemo implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * key
     */
    private Integer type;

    /**
     * 值
     */
    private BigDecimal val;

    /**
     * 添加字段
     */
    private Integer addOne;

    /**
     * 添加字段2
     */
    private Integer addTwo;

    /**
     * 名字
     */
    private Integer userName;

    /**
     * strs_user_demo
     */
    private static final long serialVersionUID = 1L;
}