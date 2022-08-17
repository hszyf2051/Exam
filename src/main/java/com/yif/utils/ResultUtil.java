package com.yif.utils;

import com.yif.vo.params.Result;

/**
 * @author Yif
 */
public class ResultUtil {
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static Result fail(String msg) {
        Result result = new Result();
        result.setCode(-1);
        result.setMsg(msg);
        return result;
    }

    public static Result failMsg(String error) {
        Result result = new Result();
        result.setCode(-1);
        result.setMsg("失败");
        result.setData(error);
        return result;
    }
}
