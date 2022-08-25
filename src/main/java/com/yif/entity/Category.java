package com.yif.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Category)实体类
 *
 * @author yif
 */
@Data
public class Category implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 二级类别id
     */
    private Integer cid;

}

