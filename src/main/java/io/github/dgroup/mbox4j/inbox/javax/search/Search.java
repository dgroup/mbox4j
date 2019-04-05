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

import io.github.dgroup.mbox4j.EmailException;
import io.github.dgroup.mbox4j.Msg;
import io.github.dgroup.mbox4j.Query;
import java.util.Collections;
import javax.mail.Folder;
import org.cactoos.BiFunc;

/**
 * The search within email folder.
 *
 * @since 0.1.0
 * @todo #/DEV Add documentation about {@link io.github.dgroup.mbox4j.inbox.javax.search}
 *  to the <em>readme.md</em>.
 */
public interface Search extends BiFunc<Query, Folder, Iterable<Msg>> {

    /**
     * Search the emails.
     * @param query The search details.
     * @param folder The email folder.
     * @return The emails.
     * @throws EmailException In the case of connectivity/transformation issues.
     */
    Iterable<Msg> apply(Query query, Folder folder) throws EmailException;

    /**
     * The search which returns empty result.
     * @since 0.1.0
     */
    class Empty implements Search {

        @Override
        public Iterable<Msg> apply(final Query query, final Folder folder) {
            return Collections.emptySet();
        }
    }
}
