package club.lanxige.bbmm.vo;

import jakarta.validation.constraints.NotNull;

public class UserDeleteVO {

    @NotNull(message = "用户ID不能为空")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
