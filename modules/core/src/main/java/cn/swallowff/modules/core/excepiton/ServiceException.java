package cn.swallowff.modules.core.excepiton;

/**
 * @author shenyu
 * @create 2019/3/15
 */
public class ServiceException extends RuntimeException {
    private Integer code;
    private String errorMessage;

    public ServiceException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public ServiceException(AbstractBaseExceptionEnum exception) {
        super(exception.getMsg());
        this.code = exception.getCode();
        this.errorMessage = exception.getMsg();
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
