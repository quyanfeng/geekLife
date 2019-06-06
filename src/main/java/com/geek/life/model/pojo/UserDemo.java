package com.geek.life.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Table(name = "strs_user_demo")
public class UserDemo implements Serializable {

    @ApiModelProperty("添加字段")
    @Column(name = "add_one")
    private Integer addOne;

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("值")
    private BigDecimal val;

    @ApiModelProperty("添加字段2")
    @Column(name = "add_two")
    private Integer addTwo;

    @ApiModelProperty("名字")
    @Column(name = "user_name")
    private Integer userName;

    @ApiModelProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("key")
    private Integer type;


}