package cn.swallowff.modules.core.system.api;

import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @description
 * @create 2019/7/15
 */
@RestController
@RequestMapping(value = "${swear.path.api}/test")
public class TestApiController {

    @PostMapping(value = "test")
    public Object testToken(){
        return BaseResp.newSuccess();
    }



}
