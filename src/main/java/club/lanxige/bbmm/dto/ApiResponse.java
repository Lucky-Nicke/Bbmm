package club.lanxige.bbmm.dto;

public class ApiResponse<T> {
    private String message;
    private boolean success;
    private T data;

    // 构造方法
    public ApiResponse() {
    }

    public ApiResponse(String message, boolean success, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    // 添加getter和setter方法
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 静态工厂方法
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, true, data);
    }

    public static <T> ApiResponse<T> success(String message) {
        return success(message, null);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(message, false, null);
    }
}
