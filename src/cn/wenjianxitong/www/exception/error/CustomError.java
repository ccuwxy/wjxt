package cn.wenjianxitong.www.exception.error;

import cn.wenjianxitong.www.utils.JsonUtils;

public abstract class CustomError extends Throwable{
    private int err_code;
    protected CustomError(int _err_code){
        this.err_code=_err_code;
    }

    public String getRetJson() {
        return JsonUtils.toJson(new ErrGsonStructure(err_code));
    }

    protected int getErr_code() {
        return err_code;
    }
}