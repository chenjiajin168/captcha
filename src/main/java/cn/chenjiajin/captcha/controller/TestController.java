package cn.chenjiajin.captcha.controller;

import cn.chenjiajin.captcha.CaptchaGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
public class TestController {

    // http://localhost/demo
    @RequestMapping("/demo")
    private void demo(HttpServletResponse response) throws Exception {

        // 获取图形验证码
        Map<String, Object> checkCode = CaptchaGenerator.generateCaptcha();
        //checkCode.forEach((k, v) -> System.out.println("k: " + k + " ,v: " + v));

        BufferedImage checkCodeImage = (BufferedImage) checkCode.get("captchaImage");
        Object calibration = checkCode.get("captchaString");
        String key = UUID.randomUUID().toString().replace("-", "");  // key作为验证码的标识返回给前端, 验证的时候前端传递过来
        log.info("获取验证码: 标识 {} , 结果 {}", key, calibration.toString());

        ImageIO.write(checkCodeImage, "png", response.getOutputStream());
    }


}
