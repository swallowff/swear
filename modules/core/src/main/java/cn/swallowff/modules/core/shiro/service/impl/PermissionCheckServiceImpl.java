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
package cn.swallowff.modules.core.shiro.service.impl;

import cn.swallowff.modules.core.listener.ConfigListener;
import cn.swallowff.modules.core.shiro.ShiroKit;
import cn.swallowff.modules.core.shiro.ShiroUser;
import cn.swallowff.modules.core.shiro.service.PermissionCheckService;
import cn.swallowff.modules.core.util.HttpContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 权限自定义检查
 */
@Service
@Transactional(readOnly = true)
public class PermissionCheckServiceImpl implements PermissionCheckService {

    @Override
    public boolean check(String[] permissions) {
        ShiroUser user = ShiroKit.getUser();
        if (null == user) {
            return false;
        }
        for (String permission : permissions){
            if (!ShiroKit.hasPermission(permission)){
                //如果任一权限值校验失败,则验证失败
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkRole(String[] roleCodes) {
        ShiroUser user = ShiroKit.getUser();
        if (null == user) {
            return false;
        }
        return ShiroKit.hasAnyRoles(roleCodes);
    }
}
