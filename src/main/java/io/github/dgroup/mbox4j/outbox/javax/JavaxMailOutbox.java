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
import io.github.dgroup.mbox4j.Msg;
import io.github.dgroup.mbox4j.Outbox;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import org.cactoos.BiFunc;
import org.cactoos.Scalar;
import org.cactoos.scalar.Sticky;

/**
 * The email postman based on {@link javax.mail} framework.
 *
 * @since 0.1.0
 */
public class JavaxMailOutbox implements Outbox {

    /**
     * The function to prepare the {@link javax.mail.Message}.
     */
    private final BiFunc<Session, Msg, Message> fmsg;

    /**
     * The mail session with protocol providers.
     */
    private final Scalar<Session> session;

    /**
     * Ctor.
     * @param props The postman configuration properties.
     */
    public JavaxMailOutbox(final Properties props) {
        this(() -> props);
    }

    /**
     * Ctor.
     * @param props The postman configuration properties.
     */
    public JavaxMailOutbox(final Scalar<Properties> props) {
        this(new MimeMsg(), new Sticky<>(new SessionOf(props)));
    }

    /**
     * Ctor.
     * @param fmsg The function to prepare the {@link javax.mail.Message}.
     * @param sssn The mail session with protocol providers.
     * @see javax.mail.Message
     * @see javax.mail.Session
     */
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public JavaxMailOutbox(final BiFunc<Session, Msg, Message> fmsg, final Scalar<Session> sssn) {
        this.fmsg = fmsg;
        this.session = sssn;
    }

    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public final void send(final Msg msg) throws EmailException {
        try {
            Transport.send(
                this.fmsg.apply(this.session.value(), msg)
            );
            // @checkstyle IllegalCatchCheck (3 lines)
        } catch (final Exception cause) {
            throw new EmailException(cause);
        }
    }
}
