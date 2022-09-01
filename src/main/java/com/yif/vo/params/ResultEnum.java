package com.yif.vo.params;

/**
 * @author Yif
 */
@SuppressWarnings("all")
public enum ResultEnum {

    PARAMS_ERROR(100, "参数有误"),
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
    ACCOUNT_USERNAME_NOT_EXIST(111, "用户名不能为空"),
    ACCOUNT_PWD_NOT_EXIST(112, "密码不能为空"),
    REQUIRE_USERNAME(504, "必须填写用户名"),
    UPLOAD_FAILED(20001, "上传失败"),
    REGISTER_FAILED(20002, "注册失败"),

    NO_PERMISSION(70001, "无访问权限"),
    SESSION_TIME_OUT(90001, "会话超时"),
    NO_LOGIN(90002, "未登录"),
    NEED_LOGIN(401,"需要登陆后操作"),
    LOGIN_ERROR(402,"用户名或密码错误"),
    AUTHENTICATE_FAILED(403,"认证授权失败"),
    SYSTEM_ERROR(505,"出现错误"),
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