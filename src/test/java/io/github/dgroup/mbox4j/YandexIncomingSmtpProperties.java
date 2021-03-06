/*
 * MIT License
 *
 * Copyright (c) 2019 Yurii Dubinka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom
 * the Software is  furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.dgroup.mbox4j;

import io.github.dgroup.term4j.arg.ArgNotFoundException;
import io.github.dgroup.term4j.arg.PropOf;
import java.util.Properties;
import org.cactoos.Scalar;

/**
 * Yandex SMTP server incoming properties.
 *
 * @since 0.1.0
 */
public final class YandexIncomingSmtpProperties implements Scalar<Properties> {

    @Override
    public Properties value() throws ArgNotFoundException {
        final Properties props = new Properties();
        props.setProperty("mail.smtp.auth", Boolean.TRUE.toString());
        props.setProperty("mail.smtp.starttls.enable", Boolean.TRUE.toString());
        props.setProperty("mail.smtp.host", "imap.yandex.com");
        props.setProperty("mail.smtp.port", "993");
        props.setProperty("username", new PropOf("LL.yandex.user").value());
        props.setProperty("password", new PropOf("LL.yandex.pass").value());
        return props;
    }

}
