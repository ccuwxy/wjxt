package cn.wenjianxitong.www.exception.file;

import cn.wenjianxitong.www.exception.error.CustomError;

public class InvalidParamsException extends CustomError {
    private static final int err_code = 401;
    public InvalidParamsException() {
        super(err_code);
    }
}
