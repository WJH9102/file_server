package xyz.ibytecode.file_server.utils;

import java.io.Serializable;
import lombok.Data;

@Data
public class RsBody<T> implements Serializable, Cloneable {
    private static final long serialVersionUID = 6334971409410300129L;
    private T data;
    private String code = "1";
    private String message = "成功";

    public RsBody<T> clone() {
        RsBody o = null;

        try {
            o = (RsBody)super.clone();
        } catch (CloneNotSupportedException var3) {
            var3.printStackTrace();
        }

        return o;
    }

    public RsBody() {
    }

    public RsBody(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return Status.Bu_SUCCESS.getCode().equals(this.code);
    }

    public RsBody(T data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public RsBody<T> setBody(Boolean flag) {
        if (flag) {
            this.setCode(Status.Bu_SUCCESS.getCode());
            this.setMessage(Status.Bu_SUCCESS.getValue());
        } else {
            this.setCode(Status.BU_FAILD.getCode());
            this.setMessage(Status.BU_FAILD.getValue());
        }
        return this;
    }

    public RsBody<T> setDataBody(T t) {
        this.setData(t);
        this.setCode(Status.Bu_SUCCESS.getCode());
        this.setMessage(Status.Bu_SUCCESS.getValue());
        return this;
    }

    public String toString() {
        return "ResponseBody [data=" + this.data + ",code=" + this.code + ",message=" + this.message + "]";
    }
}
