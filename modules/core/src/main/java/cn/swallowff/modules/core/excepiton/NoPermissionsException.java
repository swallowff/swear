package cn.swallowff.modules.core.excepiton;

/**
 * @author shenyu
 * @create 2019/7/8
 */
public class NoPermissionsException extends ServiceException {
    public NoPermissionsException(AbstractBaseExceptionEnum exception) {
        super(exception);
    }
}
