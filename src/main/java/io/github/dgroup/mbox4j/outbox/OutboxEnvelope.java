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

import io.github.dgroup.mbox4j.EmailException;
import io.github.dgroup.mbox4j.Msg;
import io.github.dgroup.mbox4j.Outbox;

/**
 * The envelope for {@link Outbox}.
 *
 * @since 0.1.0
 */
public class OutboxEnvelope implements Outbox {

    /**
     * Original.
     */
    private final Outbox origin;

    /**
     * Ctor.
     * @param origin The original postman.
     */
    public OutboxEnvelope(final Outbox origin) {
        this.origin = origin;
    }

    @Override
    public final void send(final Msg msg) throws EmailException {
        this.origin.send(msg);
    }
}
