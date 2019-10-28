package cn.swallowff.admin.cmomon.excepiton;

/**
 * @author shenyu
 * @create 19-6-26
 */
public class BizException extends ServiceException {
    public BizException(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public BizException(AbstractBaseExceptionEnum exception) {
        super(exception);
    }
}
