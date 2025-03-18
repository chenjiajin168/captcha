package cn.chenjiajin.captcha.controller;

import cn.chenjiajin.captcha.FixedArithmeticCaptcha;
import cn.chenjiajin.captcha.vo.CaptchaVO;
import com.wf.captcha.base.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
public class CaptchaController {


    // http://localhost/login
    @RequestMapping("/login")
    private String lock() {
        return "login";
    }


    private static final Map<String, String> map = new HashMap<>();

    // 获取验证码 返回数据JSON给前端
    // http://localhost/getCaptcha
    @ResponseBody
    @RequestMapping("/getCaptcha")
    private CaptchaVO getCaptcha() {
        String key = UUID.randomUUID().toString().replace("-", "");  // key作为验证码的标识返回给前端, 验证的时候前端传递过来

        Captcha captcha = new FixedArithmeticCaptcha(111, 36);
        captcha.setLen(2);
        String captchaValue = captcha.text();
        if (captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        System.err.println("验证的计算结果 = " + captchaValue);   // 验证的计算结果
        map.put(key, captchaValue);

        CaptchaVO vo = new CaptchaVO();
        vo.setKey(key);
        vo.setImg(captcha.toBase64());
        return vo;

    }


    // 校验 http://localhost/calibration?key=xxx&checkCode=123
    @ResponseBody
    @PostMapping("/calibration")
    private boolean calibration(String key, String checkCode) {
        log.info("标识: {} , 验证值: {}", key, checkCode);
        String checkCodeValue = map.get(key);
        if (StringUtils.hasText(checkCodeValue) && StringUtils.hasText(checkCode)) {
            if (checkCodeValue.equals(checkCode)) {
                //map.remove(key);
                return true;
            }
        }
        return false;
    }


}
