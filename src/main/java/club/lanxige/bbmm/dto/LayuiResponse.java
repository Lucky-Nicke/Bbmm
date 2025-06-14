package club.lanxige.bbmm.dto;

import lombok.Data;

import java.util.List;

public class LayuiResponse<T> {
    private int code;
    private String msg;
    private long count;
    private List<T> data;

    // 手动添加setter和getter方法
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

    // 修改success方法，明确泛型类型
    public static <T> LayuiResponse<T> success(T data, long count) {
        LayuiResponse<T> response = new LayuiResponse<>();
        response.setCode(0);
        response.setMsg("");
        response.setCount(count);
        response.setData((List<T>) data);
        return response;
    }
}
