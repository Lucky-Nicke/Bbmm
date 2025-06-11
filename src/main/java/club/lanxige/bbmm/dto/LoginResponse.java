package club.lanxige.bbmm.dto;

public class LoginResponse {

    // 响应消息
    private String message;
    // 操作是否成功
    private boolean success;
    // 重定向URL
    private String redirectUrl;
    // 用户角色
    private String role;
    // 用户基本信息
    private UserInfo data;

    // ------------------- 构造方法 -------------------

    public LoginResponse() {
    }

    public LoginResponse(String message, boolean success, String redirectUrl, String role, UserInfo data) {
        this.message = message;
        this.success = success;
        this.redirectUrl = redirectUrl;
        this.role = role;
        this.data = data;
    }

    // ------------------- 手动实现的getter/setter -------------------

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

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

    public static LoginResponseBuilder builder() {
        return new LoginResponseBuilder();
    }

    public static class LoginResponseBuilder {
        private String message;
        private boolean success;
        private String redirectUrl;
        private String role;
        private UserInfo data;

        public LoginResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public LoginResponseBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public LoginResponseBuilder redirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
            return this;
        }

        public LoginResponseBuilder role(String role) {
            this.role = role;
            return this;
        }

        public LoginResponseBuilder data(UserInfo data) {
            this.data = data;
            return this;
        }

        public LoginResponse build() {
            return new LoginResponse(message, success, redirectUrl, role, data);
        }
    }

    public static class UserInfo {
        private Long id;
        private String username;

        // 构造方法
        public UserInfo() {
        }

        public UserInfo(Long id, String username) {
            this.id = id;
            this.username = username;
        }

        // getter/setter
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        // Builder模式实现
        public static UserInfoBuilder builder() {
            return new UserInfoBuilder();
        }

        public static class UserInfoBuilder {
            private Long id;
            private String username;

            public UserInfoBuilder id(Long id) {
                this.id = id;
                return this;
            }

            public UserInfoBuilder username(String username) {
                this.username = username;
                return this;
            }

            public UserInfo build() {
                return new UserInfo(id, username);
            }
        }
    }
}