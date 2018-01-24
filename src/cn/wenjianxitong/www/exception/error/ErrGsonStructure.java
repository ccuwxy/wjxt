package cn.wenjianxitong.www.exception.error;

public class ErrGsonStructure {
    public int err_code;

    public String reason;

    public ErrGsonStructure(int err_code) {
        this.err_code = err_code;
        this.reason=Reason.getReason(err_code);
    }

    public ErrGsonStructure(int err_code,String reason) {
        this.err_code = err_code;
        this.reason=reason;
    }

    public int getErr_code() {
        return err_code;
    }

    public String getReason() {
        return reason;
    }
}
