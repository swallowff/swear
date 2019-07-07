/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.swallowff.modules.core.aop;

import cn.swallowff.modules.core.cmomon.annotion.Permission;
import cn.swallowff.modules.core.constant.exceptionenum.BizExceptionEnum;
import cn.swallowff.modules.core.excepiton.NoPermissionsException;
import cn.swallowff.modules.core.excepiton.ServiceException;
import cn.swallowff.modules.core.shiro.service.PermissionCheckService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.naming.NoPermissionException;
import java.lang.reflect.Method;

/**
 * 权限检查的aop
 *
 * @author fengshuonan
 * @date 2017-07-13 21:05
 */
@Aspect
@Component
@Order(200)
public class PermissionAop {

    @Autowired
    private PermissionCheckService check;

    @Pointcut(value = "@annotation(cn.swallowff.modules.core.cmomon.annotion.Permission)")
    private void cutPermission() {

    }

    @Around("cutPermission()")
    public Object doPermission(ProceedingJoinPoint point) throws NoPermissionsException {
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        Permission permission = method.getAnnotation(Permission.class);
        String[] permissions = permission.value();
        String[] roleCodes = permission.roleCode();
        if (permissions.length > 0) {
            checkPermissions(permissions);
        }
        if (roleCodes.length > 0){
            checkRoleCodes(roleCodes);
        }
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    private void checkPermissions(String[] permissons) throws NoPermissionsException{
        boolean flag = check.check(permissons);
        if (!flag){
            throw new NoPermissionsException(BizExceptionEnum.NO_PERMITION);
        }
    }

    private void checkRoleCodes(String[] roleCodes) throws NoPermissionsException{
        boolean flag = check.checkRole(roleCodes);
        if (!flag){
            throw new NoPermissionsException(BizExceptionEnum.NO_PERMITION);
        }
    }

}
