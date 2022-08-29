package com.yif.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "Project对象", description = "商品列表")
@EqualsAndHashCode(callSuper = false)
public class Project {
    /**
     * 商品id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String title;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品卖点
     */
    private String sellPoint;

    /**
     * 商品价格
     */
    private String price;

    /**
     * cid
     */
    private String cid;

    /**
     * 规格类目
     */
    private String category;

    /**
     * 商品数量
     */
    private String num;

    private String barcode;

    /**
     * 商品状态
     */
    private String status;

    /**
     * 创建时间
     */
    private String created;
    private String updated;

    /**
     * 商品描述
     */
    private String descs;
    private String paramsInfo;
}
