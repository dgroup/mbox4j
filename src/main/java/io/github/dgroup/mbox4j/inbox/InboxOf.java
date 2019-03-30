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

package io.github.dgroup.mbox4j.inbox;

import io.github.dgroup.mbox4j.EmailException;
import io.github.dgroup.mbox4j.Msg;
import io.github.dgroup.mbox4j.Query;
import org.cactoos.Func;

/**
 * The email inbox.
 *
 * @see io.github.dgroup.mbox4j.Inbox
 * @see io.github.dgroup.mbox4j.inbox.javax.func
 *
 * @since 0.1.0
 */
public final class InboxOf extends InboxEnvelope {

    /**
     * Ctor.
     * @param fnc The function to fetch email messages based on query.
     * @see io.github.dgroup.mbox4j.inbox.javax.func
     */
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public InboxOf(final Func<Query, Iterable<Msg>> fnc) {
        super(query -> {
            try {
                return fnc.apply(query);
                // @checkstyle IllegalCatchCheck (3 lines)
            } catch (final Exception cause) {
                throw new EmailException(cause);
            }
        });
    }
}
