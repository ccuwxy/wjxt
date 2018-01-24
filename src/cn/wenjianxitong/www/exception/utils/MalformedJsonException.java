package cn.wenjianxitong.www.exception.utils;

import cn.wenjianxitong.www.exception.error.CustomError;

public class MalformedJsonException extends CustomError {
    private static final int err_code = 405;
    public MalformedJsonException() {
        super(err_code);
    }
}
