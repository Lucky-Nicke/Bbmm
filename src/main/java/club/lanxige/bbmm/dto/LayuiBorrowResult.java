package club.lanxige.bbmm.dto;

import java.io.Serializable;
import java.util.List;

public class LayuiBorrowResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private long count;
    private T data;

    public static <T> LayuiBorrowResult<T> success(T data) {
        LayuiBorrowResult<T> LayuiBorrowResult = new LayuiBorrowResult<>();
        LayuiBorrowResult.setCode(0);
        LayuiBorrowResult.setMsg("");
        LayuiBorrowResult.setData(data);
        if (data instanceof List) {
            LayuiBorrowResult.setCount(((List<?>) data).size());
        } else {
            LayuiBorrowResult.setCount(1);
        }
        return LayuiBorrowResult;
    }

    // Getters and Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}    