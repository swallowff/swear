package cn.swallowff.modules.core.modules.system.api.ctrl;

import cn.swallowff.modules.core.cmomon.resp.BaseResp;
import cn.swallowff.modules.core.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Administrator
 * @description
 * @create 2019/7/15
 */
@RestController
@RequestMapping(value = "${swear.path.api}/test")
public class TestApiController {

    @PostMapping(value = "test")
    public Object testToken(String token) {
        Claims claims = JwtTokenUtil.getClaimFromToken(token);
        Date expiredDate = JwtTokenUtil.getExpirationDateFromToken(token);
        String audience = JwtTokenUtil.getAudienceFromToken(token);
        Date issuedAt = JwtTokenUtil.getIssuedAtDateFromToken(token);
        String userName = JwtTokenUtil.getUsernameFromToken(token);
        return BaseResp.newSuccess();
    }


}
