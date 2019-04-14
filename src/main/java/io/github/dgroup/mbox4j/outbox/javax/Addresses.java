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

import java.util.Set;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.collection.Mapped;
import org.cactoos.text.Joined;

/**
 * Parse the sequence of email addresses into {@link javax.mail.internet.InternetAddress}
 *  objects in accordance with RFC822 syntax.
 *
 * @since 0.1.0
 * @todo #/DEV Change the email to LowLevel case in order to prevent duplication of emails
 *  due to different case.
 */
public final class Addresses implements Scalar<InternetAddress[]> {

    /**
     * The email addresses.
     */
    private final Set<Text> emails;

    /**
     * Ctor.
     * @param emails The email addresses.
     */
    public Addresses(final Set<Text> emails) {
        this.emails = emails;
    }

    @Override
    public InternetAddress[] value() throws AddressException {
        return InternetAddress.parse(
            new Joined(",", new Mapped<>(Text::asString, this.emails))
                .toString()
        );
    }
}
