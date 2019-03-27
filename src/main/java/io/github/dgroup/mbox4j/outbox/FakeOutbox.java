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

package io.github.dgroup.mbox4j.outbox;

import io.github.dgroup.mbox4j.Msg;
import io.github.dgroup.mbox4j.Outbox;
import java.util.Collection;
import java.util.LinkedList;
import org.cactoos.collection.Mapped;

/**
 * The fake implementation of {@link Outbox} for unit testing purposes.
 *
 * @since 0.1.0
 */
public final class FakeOutbox implements Outbox {

    /**
     * The email messages which were sent.
     */
    private final Collection<Msg> sent;

    /**
     * Ctor.
     */
    public FakeOutbox() {
        this.sent = new LinkedList<>();
    }

    @Override
    public void send(final Msg msg) {
        this.sent.add(msg);
    }

    /**
     * The messages sent over postman.
     * @return The messages.
     */
    public Collection<Msg> sentMessages() {
        return this.sent;
    }

    /**
     * The message's contents sent over postman.
     * @return The content(s).
     */
    public Collection sentContents() {
        return new Mapped<>(Msg::body, this.sentMessages());
    }
}
