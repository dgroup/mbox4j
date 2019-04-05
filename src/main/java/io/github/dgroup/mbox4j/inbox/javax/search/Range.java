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
import org.cactoos.iterable.IterableOf;

/**
 * Search mode within the email folder which is fetching emails
 *  based on their indexes using {@link javax.mail}.
 *
 * The indexing starts from <em>1</em>.
 *
 * @since 0.1.0
 * @todo #/DEV Range#search - add integration test
 */
public final class Range extends SearchOf {

    /**
     * Ctor.
     */
    public Range() {
        super(
            (query, folder) -> {
                final String zero = "0";
                final int start = Integer.parseInt(query.mode().argument("start", zero));
                final int end = Integer.parseInt(query.mode().argument("end", zero));
                return new IterableOf<>(folder.getMessages(start, end));
            },
            Mode.RANGE
        );
    }

}
