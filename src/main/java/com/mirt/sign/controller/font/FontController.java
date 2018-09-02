package com.mirt.sign.controller.font;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前段路由
 *
 * @author zyq
 * @date 18-9-2.
 */
@Controller
public class FontController {

    @RequestMapping("/")
    public String welcomePage() {
        return "index";
    }
}
