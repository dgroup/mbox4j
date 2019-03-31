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

package io.github.dgroup.mbox4j.outbox.javax;

import io.github.dgroup.mbox4j.EmailException;
import io.github.dgroup.mbox4j.YandexOutgoingSmtpProperties;
import io.github.dgroup.mbox4j.msg.MsgOf;
import io.github.dgroup.term4j.arg.ArgNotFoundException;
import io.github.dgroup.term4j.arg.PropOf;
import java.io.File;
import java.util.Collections;
import java.util.Set;
import org.cactoos.set.SetOf;
import org.junit.Test;

/**
 * Test case for {@link JavaxMailOutbox}.
 *
 * @since 0.1.0
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle LocalFinalVariableNameCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class JavaxMailOutboxTestIT {

    /**
     * Integration test for email message sending procedure.
     * @throws EmailException In case issues during sending.
     * @throws ArgNotFoundException In case if user credentials
     *  were not specified during the testing procedure.
     */
    @Test
    public void sendFromYandexAccount() throws EmailException, ArgNotFoundException {
        final String from = new PropOf("LL.yandex.user").value();
        final Set<String> to = new SetOf<>(new PropOf("LL.yandex.to.user").value());
        final Set<String> cc = Collections.emptySet();
        final Set<String> bcc = Collections.emptySet();
        final Set<File> attachments = new SetOf<>(new File(".gitignore"), new File(".pdd"));
        new JavaxMailOutbox(
            new YandexOutgoingSmtpProperties()
        ).send(
            new MsgOf(from, to, cc, bcc, "Testing subj", "I'm simple and i know it.", attachments)
        );
    }
}
