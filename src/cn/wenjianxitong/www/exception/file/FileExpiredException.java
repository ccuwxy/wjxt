package cn.wenjianxitong.www.exception.file;

import cn.wenjianxitong.www.exception.error.CustomError;

public class FileExpiredException extends CustomError{
    private static final int err_code = 423;
    public FileExpiredException() {
        super(err_code);
    }
}
