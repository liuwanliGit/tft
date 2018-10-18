package com.tft.framework.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/13 15:50
 *
 * @ClassName TftPasswordEncoder
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class TftPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}
