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

package io.github.dgroup.mbox4j.inbox.javax.search;

import io.github.dgroup.mbox4j.query.Mode;
import java.util.concurrent.atomic.AtomicInteger;
import javax.mail.Flags.Flag;
import org.cactoos.iterable.Filtered;

/**
 * Search mode within the email folder which is fetching all recent email.
 *  using {@link javax.mail}.
 *
 * @since 0.1.0
 * @todo #/DEV Recent#search - add integration test
 */
public final class Recent extends SearchOf {

    /**
     * Ctor.
     */
    public Recent() {
        super(
            (query, folder) -> {
                final int quantity = folder.getNewMessageCount();
                final AtomicInteger added = new AtomicInteger(0);
                return new Filtered<>(
                    msg -> msg.isSet(Flag.RECENT) && added.incrementAndGet() < quantity,
                    folder.getMessages()
                );
            },
            Mode.RECENT
        );
    }

}
