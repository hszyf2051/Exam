package com.yif.vo.params;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Yif
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        Result result = new Result();
        // 成功返回值为200
        result.setCode(200);
        result.setMsg("请求成功");
        result.data = data;
        return result;
    }

    public static Result fail(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

}
