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

package io.github.dgroup.mbox4j.inbox.func.javax;

import io.github.dgroup.mbox4j.Msg;
import io.github.dgroup.mbox4j.Query;
import io.github.dgroup.mbox4j.inbox.InboxOf;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.collection.Mapped;

/**
 * The function to evaluate the messages using {@link javax.mail} framework.
 *
 * Example:
 * {@code
 * final Inbox inbox = new InboxOf(
 *     new JavaxMailInbox(properties)
 * );
 * }
 *
 * @see InboxOf
 *
 * @since 0.1.0
 * @todo #/DEV Implement search to the javax instead of fetching all messages.
 *  The search should be based on {@link Query}.
 */
public final class JavaxMailInbox implements Func<Query, Iterable<Msg>> {

    /**
     * The function to evaluate the {@link javax.mail.Store}.
     */
    private final Func<Query, Store> fstore;

    /**
     * The function to map {@link javax.mail.Message} and {@link Msg}.
     */
    private final Func<Message, Msg> fmsg;

    /**
     * Ctor.
     * @param props The mail server connection properties.
     */
    public JavaxMailInbox(final Scalar<Properties> props) {
        this(
            query -> {
                final Properties smtp = props.value();
                final Session session = Session.getDefaultInstance(smtp);
                final Store store = session.getStore(query.protocol());
                store.connect(
                    smtp.getProperty("mail.smtp.host"),
                    smtp.getProperty("username"),
                    smtp.getProperty("password")
                );
                return store;
            },
            new ToMsg()
        );
    }

    /**
     * Ctor.
     * @param fstore The function to evaluate a {@link Store} in order to
     *  manipulate with email messages.
     * @param fmsg The function to map {@link javax.mail.Message} and {@link Msg}.
     * @todo #/DEV Make this ctor with {@code Func<Query,Store>} public.
     *  For now this ctor has a side effect as it closes
     *  the {@link javax.mail.Store} and {@link javax.mail.Folder}.
     */
    private JavaxMailInbox(final Func<Query, Store> fstore, final Func<Message, Msg> fmsg) {
        this.fstore = fstore;
        this.fmsg = fmsg;
    }

    @Override
    public Iterable<Msg> apply(final Query query) throws Exception {
        final Store store = this.fstore.apply(query);
        final Folder folder = store.getFolder(query.folder());
        try {
            folder.open(Folder.READ_ONLY);
            return new ArrayList<>(
                new Mapped<>(this.fmsg, folder.getMessages())
            );
        } finally {
            folder.close(true);
            store.close();
        }
    }
}
