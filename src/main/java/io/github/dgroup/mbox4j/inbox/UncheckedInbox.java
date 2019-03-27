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
import io.github.dgroup.mbox4j.Inbox;
import io.github.dgroup.mbox4j.Msg;
import io.github.dgroup.mbox4j.Query;
import io.github.dgroup.mbox4j.UncheckedEmailException;

/**
 * The inbox which doesn't throw the checked exception.
 *
 * @since 0.1.0
 */
public final class UncheckedInbox implements Inbox {

    /**
     * THe original inbox to wrap checked exception.
     */
    private final Inbox origin;

    /**
     * Ctor.
     * @param origin The original inbox.
     */
    public UncheckedInbox(final Inbox origin) {
        this.origin = origin;
    }

    @Override
    public Iterable<Msg> read(final Query query) {
        try {
            return this.origin.read(query);
        } catch (final EmailException cause) {
            throw new UncheckedEmailException(cause);
        }
    }
}
