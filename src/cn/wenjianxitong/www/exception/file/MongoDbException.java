package cn.wenjianxitong.www.exception.file;

import cn.wenjianxitong.www.exception.error.CustomError;

public class MongoDbException extends CustomError {
    private static final int err_code = 421;
    public MongoDbException() {
        super(err_code);
    }
}
