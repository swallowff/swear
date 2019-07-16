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
package cn.swallowff.modules.core.filter;

import cn.swallowff.common.json.JacksonUtil;
import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.constant.sys.JwtConstants;
import cn.swallowff.modules.core.constant.exceptionenum.BizExceptionEnum;
import cn.swallowff.modules.core.constant.states.ResponseState;
import cn.swallowff.modules.core.excepiton.ServiceException;
import cn.swallowff.modules.core.util.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Rest Api接口鉴权
 *
 * @author swallowff
 * @Date 2019/7/20 23:11
 */
public class RestApiInteceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
            return true;
        }
        return check(request, response);
    }

    private boolean check(HttpServletRequest request, HttpServletResponse response) {
        if (request.getServletPath().equals(JwtConstants.AUTH_PATH)) {
            return true;
        }
        final String requestHeader = request.getHeader(JwtConstants.AUTH_HEADER);
        String authToken;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);

            //验证token是否过期,包含了验证jwt是否正确
            try {
                boolean flag = JwtTokenUtil.isTokenExpired(authToken);
                if (flag) {
                    renderJson(response, new BaseResp(BizExceptionEnum.TOKEN_EXPIRED.getCode(), BizExceptionEnum.TOKEN_EXPIRED.getMsg()));
                    return false;
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                renderJson(response, new BaseResp(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMsg()));
                return false;
            }
        } else {
            //header没有带Bearer字段
            renderJson(response, new BaseResp(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMsg()));
            return false;
        }
        return true;
    }

    private static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JacksonUtil.toJson(jsonObject));
        } catch (IOException var3) {
            throw new ServiceException(ResponseState.SYSTEM_ERROR);
        }
    }

}
