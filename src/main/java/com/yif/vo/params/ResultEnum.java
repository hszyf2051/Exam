package com.yif.vo.params;

/**
 * @author Yif
 */
@SuppressWarnings("all")
public enum ResultEnum {

    PARAMS_ERROR(100, "参数有误"),
    ACCOUNT_PWD_NOT_EXIST(101, "用户不存在，请重新登录"),
    ACCOUNT_PWD_ERROR(102, "密码输入错误"),
    TOKEN_ILLEGAL(103, "token不合法"),
    TOKEN_NOT_EXIST(104, "token不存在"),
    ACCOUNT_EXIST(105, "账号已存在"),
    ACCOUNT_NOT_EXIST(105, "账号不存在"),
    QUESTION_NOT_EXIST(106, "问题不存在"),
    QUESTION_EXIST(107, "问题已存在"),
    ANSWER_EXIST(108, "答案已存在"),
    ANSWER_NOT_EXIST(109, "答案不存在"),
    CATEGORY_NOT_EXIST(110, "类别不存在"),
    NO_PERMISSION(70001, "无访问权限"),
    SESSION_TIME_OUT(90001, "会话超时"),
    NO_LOGIN(90002, "未登录"),
    ;

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}