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
import io.github.dgroup.mbox4j.inbox.javax.ToMsg;
import java.util.ArrayList;
import java.util.Objects;
import javax.mail.Folder;
import javax.mail.Message;
import org.cactoos.BiFunc;
import org.cactoos.Func;
import org.cactoos.collection.Mapped;

/**
 * The default search.
 *
 * @since 0.1.0
 */
public class SearchOf implements Search {

    /**
     * The function to filter messages from particular email folder.
     */
    private final BiFunc<Query, Folder, Iterable<Message>> fltr;

    /**
     * The name of the search mode.
     */
    private final String mode;

    /**
     * The function to map {@link Message} to {@link Msg}.
     */
    private final Func<Message, Msg> fmap;

    /**
     * Ctor.
     * @param fltr The function to filter the messages from.
     * @param mode The search mode.
     */
    public SearchOf(final BiFunc<Query, Folder, Iterable<Message>> fltr, final String mode) {
        this(fltr, mode, new ToMsg());
    }

    /**
     * Ctor.
     * @param fltr The function to filter the messages from.
     * @param mode The search mode.
     * @param fmap The function to map {@link Message} to {@link Msg}.
     */
    public SearchOf(
        final BiFunc<Query, Folder, Iterable<Message>> fltr,
        final String mode,
        final Func<Message, Msg> fmap
    ) {
        this.fmap = fmap;
        this.fltr = fltr;
        this.mode = mode;
    }

    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public final Iterable<Msg> apply(final Query query, final Folder folder) throws EmailException {
        try {
            return new ArrayList<>(
                new Mapped<>(this.fmap, this.fltr.apply(query, folder))
            );
            // @checkstyle IllegalCatchCheck (3 lines)
        } catch (final Exception cause) {
            throw new EmailException(cause);
        }
    }

    @Override
    public final int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    @SuppressWarnings("PMD.OnlyOneReturn")
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Search)) {
            return false;
        }
        final Search that = (Search) obj;
        return Objects.equals(this.toString(), that.toString());
    }

    @Override
    public final String toString() {
        return this.mode;
    }
}
