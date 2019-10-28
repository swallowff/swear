package cn.swallowff.admin.modules.system.api.ctrl;

import cn.swallowff.admin.cmomon.resp.BaseResp;
import cn.swallowff.admin.util.JwtTokenUtil;
import cn.swallowff.admin.util.RedisTemplateUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Administrator
 * @description
 * @create 2019/7/15
 */
@RestController
@RequestMapping(value = "${swear.path.api}/auth/test")
public class TestApiController {
    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @PostMapping(value = "test")
    public Object testToken(String token) {
        Claims claims = JwtTokenUtil.getClaimFromToken(token);
        Date expiredDate = JwtTokenUtil.getExpirationDateFromToken(token);
        String audience = JwtTokenUtil.getAudienceFromToken(token);
        Date issuedAt = JwtTokenUtil.getIssuedAtDateFromToken(token);
        String userName = JwtTokenUtil.getUsernameFromToken(token);
        return BaseResp.newSuccess();
    }

    @GetMapping(value = "testRedis")
    public Object testRedis(){
        redisTemplateUtil.set("testRedis","ok");
        return BaseResp.newSuccess();
    }


}
