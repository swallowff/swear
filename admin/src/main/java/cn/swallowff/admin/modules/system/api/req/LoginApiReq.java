package cn.swallowff.admin.modules.system.api.req;

import javax.validation.constraints.NotBlank;

/**
 * @author Administrator
 * @description
 * @create 2019/7/16
 */
public class LoginApiReq {
    @NotBlank(message = "account不能为空")
    private String account;
    @NotBlank(message = "password不能为空")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
