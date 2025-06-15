package club.lanxige.bbmm.dto;

import club.lanxige.bbmm.entity.Borrow;

import java.util.List;

public class LayuiBorrowBookResponse {
    private int code;
    private String msg;
    private long count;
    private List<Borrow> data;

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

    public List<Borrow> getData() {
        return data;
    }

    public void setData(List<Borrow> data) {
        this.data = data;
    }
}