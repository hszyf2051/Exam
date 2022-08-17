package com.yif.vo.params;

import lombok.Data;

/**
 * @author Yif
 */
@Data
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;


}
