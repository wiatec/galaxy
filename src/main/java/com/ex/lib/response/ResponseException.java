package com.ex.lib.response;

/**
 * @author patrick
 */
public class ResponseException extends RuntimeException {

    private int code;

    public ResponseException(EnumResponseStatus enumResponseStatus) {
        super(enumResponseStatus.getMessage());
        this.code = enumResponseStatus.getCode();
    }

    public ResponseException(Response response) {
        super(response.getMsg());
        this.code = response.getCode();
    }

    public ResponseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ResponseException(String code, String message) {
        super(message);
        this.code = 500;
    }

    public ResponseException(String message) {
        super(message);
        this.code = 500;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
