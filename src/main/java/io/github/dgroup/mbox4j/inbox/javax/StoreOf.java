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
package io.github.dgroup.mbox4j.inbox.javax;

import io.github.dgroup.mbox4j.Query;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Store;
import org.cactoos.Func;
import org.cactoos.Scalar;

/**
 * The function to evaluate the {@link javax.mail.Store} based on SMTP properties.
 *
 * @since 0.1.0
 */
public final class StoreOf implements Func<Query, Store> {

    /**
     * The SMTP connectivity properties.
     */
    private final Scalar<Properties> props;

    /**
     * Ctor.
     * @param props The SMTP connectivity properties.
     */
    public StoreOf(final Scalar<Properties> props) {
        this.props = props;
    }

    @Override
    public Store apply(final Query query) throws Exception {
        final Properties smtp = this.props.value();
        final Session session = Session.getDefaultInstance(smtp);
        final Store store = session.getStore(query.protocol());
        store.connect(
            smtp.getProperty("mail.smtp.host"),
            smtp.getProperty("username"),
            smtp.getProperty("password")
        );
        return store;
    }
}
