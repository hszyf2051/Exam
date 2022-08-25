package com.yif.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Content)实体类
 *
 * @author yif
 */
@Data
public class Content implements Serializable {

    private Integer id;

    private String name;

    private Integer pid;

}

