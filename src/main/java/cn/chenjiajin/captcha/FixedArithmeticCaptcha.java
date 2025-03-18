package cn.chenjiajin.captcha;

import com.wf.captcha.ArithmeticCaptcha;

/**
 * 自定义格式验证码
 * @author ChenJiaJin
 * @date 2022/1/10
 */
public class FixedArithmeticCaptcha extends ArithmeticCaptcha {

    /**
     * 获取父类的对象
     *
     * @param width  宽
     * @param height 高
     */
    public FixedArithmeticCaptcha(int width, int height) {
        super(width, height);
    }

    /**
     * 重写父类的alphas方法
     */
    @Override
    protected char[] alphas() {
        // 生成随机数字和运算符
        int n1 = num(1, 10);
        int n2 = num(1, 10);
        int opt = num(3);

        // 计算结果
        int res = new int[]{n1 + n2, n1 - n2, n1 * n2}[opt];
        // 转换为字符运算符
        char optChar = "+-x".charAt(opt);

        this.setArithmeticString(String.format("%s%c%s=?", n1, optChar, n2));
        this.chars = String.valueOf(res);

        return chars.toCharArray();
    }


}
