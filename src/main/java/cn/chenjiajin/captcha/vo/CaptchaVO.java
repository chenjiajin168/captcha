package cn.chenjiajin.captcha.vo;

import lombok.Data;

/**
 * 返回给前端的数据
 *
 * @author ChenJiaJin
 * @date 2022/1/10
 */
@Data
public class CaptchaVO {

    private String key;  // 验证码标识
    private String img;  // base64编码字符串

}
