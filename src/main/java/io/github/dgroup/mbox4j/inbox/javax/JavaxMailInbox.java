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

import io.github.dgroup.mbox4j.EmailException;
import io.github.dgroup.mbox4j.Inbox;
import io.github.dgroup.mbox4j.Msg;
import io.github.dgroup.mbox4j.Query;
import io.github.dgroup.mbox4j.inbox.javax.search.Modes;
import io.github.dgroup.mbox4j.inbox.javax.search.Search;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;
import org.cactoos.Func;
import org.cactoos.Scalar;

/**
 * The function to evaluate the messages using {@link javax.mail} framework.
 *
 * Example:
 * {@code final Inbox inbox = new JavaxMailInbox(smptProperties);}
 *
 * @since 0.1.0
 */
public final class JavaxMailInbox implements Inbox {

    /**
     * The function to evaluate the {@link javax.mail.Store}.
     */
    private final Func<Query, Store> fstore;

    /**
     * The search modes to be used within email folders.
     */
    private final Modes modes;

    /**
     * Ctor.
     * @param props The mail server connection properties.
     */
    public JavaxMailInbox(final Properties props) {
        this(() -> props);
    }

    /**
     * Ctor.
     * @param props The mail server connection properties.
     */
    public JavaxMailInbox(final Scalar<Properties> props) {
        this(props, new Modes());
    }

    /**
     * Ctor.
     * @param props The mail server connection properties.
     * @param modes The search modes to be used within email folders.
     */
    public JavaxMailInbox(final Scalar<Properties> props, final Modes modes) {
        this(new StoreOf(props), modes);
    }

    /**
     * Ctor.
     * @param fstore The function to evaluate a {@link Store} in order to
     *  manipulate with email messages.
     * @param modes The search modes to be used within email folders.
     * @todo #/DEV Make this ctor with {@code Func<Query,Store>} public.
     *  For now this ctor has a side effect as it closes
     *  the {@link javax.mail.Store} and {@link javax.mail.Folder}.
     */
    private JavaxMailInbox(final Func<Query, Store> fstore, final Modes modes) {
        this.fstore = fstore;
        this.modes = modes;
    }

    @Override
    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "PMD.EmptyCatchBlock"})
    public Iterable<Msg> read(final Query query) throws EmailException {
        Store store = null;
        Folder folder = null;
        try {
            store = this.fstore.apply(query);
            folder = store.getFolder(query.folder());
            folder.open(Folder.READ_ONLY);
            return this.modes.getOrDefault(
                query.mode().name(),
                new Search.Empty()
            ).apply(query, folder);
            // @checkstyle IllegalCatchCheck (3 lines)
        } catch (final Exception cause) {
            throw new EmailException(cause);
        } finally {
            if (folder != null) {
                try {
                    folder.close(true);
                } catch (final MessagingException cause) {
                }
            }
            if (store != null) {
                try {
                    store.close();
                } catch (final MessagingException cause) {
                }
            }
        }
    }

}
