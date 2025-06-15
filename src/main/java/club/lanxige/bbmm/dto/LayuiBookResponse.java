package club.lanxige.bbmm.dto;

import java.util.List;

public class LayuiBookResponse<T> {
    private int code;
    private String msg;
    private long count;
    private List<T> data;

    public LayuiBookResponse() {
    }

    public LayuiBookResponse(int code, String msg, long count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public static <T> LayuiBookResponse<T> success(List<T> data, long count) {
        return new LayuiBookResponse<>(0, "", count, data);
    }

    // Getter和Setter方法
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
