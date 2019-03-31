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

import java.util.ArrayList;
import java.util.Collection;
import javax.mail.Multipart;
import javax.mail.Part;
import org.cactoos.iterable.IterableEnvelope;

/**
 * Represents all parts of {@link javax.mail.Multipart} as an {@link Iterable}.
 *
 * @since 0.1.0
 * @todo #/DEV Add hierarchical unwrap as multipart can have tree-based structure.
 *  For example, the multipart can have few levels with multipart(s).
 */
public final class PartsOf extends IterableEnvelope<Part> {

    /**
     * Ctor.
     * @param multipart The content of email message.
     */
    public PartsOf(final Multipart multipart) {
        super(
            () -> {
                final int quantity = multipart.getCount();
                final Collection<Part> parts = new ArrayList<>(quantity);
                for (int idx = 0; idx < quantity; ++idx) {
                    parts.add(multipart.getBodyPart(idx));
                }
                return parts;
            }
        );
    }

}
