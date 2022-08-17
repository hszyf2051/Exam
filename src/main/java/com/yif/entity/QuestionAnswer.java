package com.yif.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Yif
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Answer对象", description="答案表")
@ToString(callSuper = true)
public class QuestionAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 答案ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 问题ID
     */

    private Long quesId;

    /**
     * 是否正确 0否 1是
     */
    private int rightFlag;

    /**
     * 选项图片
     */
    @JSONField(name = "image")
    private String image;

    /**
     * 答案内容
     */
    @JSONField(name = "content")
    private String content;
}
