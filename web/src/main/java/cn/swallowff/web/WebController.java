package cn.swallowff.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shenyu
 * @create 2019/4/26
 */
@Controller
@RequestMapping(value = "web")
public class WebController {

    @RequestMapping(value = "test")
    @ResponseBody
    public String web(){
        return "WEB-OK";
    }
}
