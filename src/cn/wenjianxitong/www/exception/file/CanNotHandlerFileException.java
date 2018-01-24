package cn.wenjianxitong.www.exception.file;

import cn.wenjianxitong.www.exception.error.CustomError;

public class CanNotHandlerFileException extends CustomError {
    private static final int err_code = 422;
    public CanNotHandlerFileException() {
        super(err_code);
    }
}
