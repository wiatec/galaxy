package com.ex.lib.response;


import com.ex.lib.Constant;

/**
 * @author patrick
 */
public enum EnumResponseStatus {

    /**
     * 请求处理成功
     */
    SUCCESS(200, Constant.Msg.SUCCESSFULLY),

    /**
     * 请求有语法错误，例如Content-Type与请求体不符或请求体无法解析
     */
    ERROR_BAD_REQUEST(400, Constant.Msg.BAD_REQUEST),

    /**
     * 请求未授权，需要登录
     */
    ERROR_UNAUTHORIZED(401, Constant.Msg.UNAUTHORIZED),

    /**
     * 请求未授权或登录超时，需要登录
     */
    ERROR_RE_LOGIN(402, Constant.Msg.LOGIN_EXPIRES),

    /**
     * 禁止操作该资源，例如权限不够等
     */
    ERROR_FORBIDDEN(403, Constant.Msg.FORBIDDEN),

    /**
     * 资源不存在
     */
    ERROR_NO_FOUND(404, Constant.Msg.NO_FOUND),

    /**
     * 请求方法错误
     */
    ERROR_METHOD_NOT_ALLOWED(405, Constant.Msg.METHOD_NOT_ALLOWED),

    /**
     * 请求超时
     */
    ERROR_TIME_OUT(406, Constant.Msg.REQUEST_TIMEOUT),

    /**
     * 请求参数格式错误
     */
    ERROR_PARAMS_FORMAT(407, Constant.Msg.REQUEST_PARAMS_INCORRECT),

    /**
     * 服务器内部错误
     */
    ERROR_INTERNAL_SERVER(500, Constant.Msg.INTERNAL_SERVER_ERROR),

    /**
     * 服务器内部,SQL执行错误
     */
    ERROR_INTERNAL_SERVER_SQL(590, Constant.Msg.INTERNAL_SERVER_ERROR_SQL),

    /**
     * 服务器内部,SQL 插入错误
     */
    ERROR_INTERNAL_SERVER_SQL_I(591, Constant.Msg.INTERNAL_SERVER_ERROR_SQL_I),

    /**
     * 服务器内部,SQL 删除错误
     */
    ERROR_INTERNAL_SERVER_SQL_D(592, Constant.Msg.INTERNAL_SERVER_ERROR_SQL_D),

    /**
     * 服务器内部,SQL 查询错误
     */
    ERROR_INTERNAL_SERVER_SQL_Q(593, Constant.Msg.INTERNAL_SERVER_ERROR_SQL_Q),

    /**
     * 服务器内部,SQL 更新错误
     */
    ERROR_INTERNAL_SERVER_SQL_U(594, Constant.Msg.INTERNAL_SERVER_ERROR_SQL_U),

    /**
     * token 错误
     */
    ERROR_ACCESS_TOKEN(6006, Constant.Msg.ACCESS_TOKEN_ERROR),
    /**
     * token 未提供
     */
    ERROR_ACCESS_TOKEN_EMPTY(6006, Constant.Msg.ACCESS_TOKEN_NO_PROVIDE),
    /**
     * token 过期
     */
    ERROR_ACCESS_TOKEN_EXPIRES(6006, Constant.Msg.ACCESS_TOKEN_EXPIRES),

    ;

    private int code;
    private String message;

    EnumResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
