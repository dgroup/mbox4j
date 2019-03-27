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

package io.github.dgroup.mbox4j.msg;

import io.github.dgroup.mbox4j.Msg;
import java.io.File;
import java.util.Collections;
import java.util.Set;
import org.cactoos.Text;
import org.cactoos.set.SetOf;
import org.cactoos.text.TextOf;

/**
 * The immutable email message.
 *
 * @since 0.1.0
 */
@SuppressWarnings("PMD.ShortMethodName")
public final class MsgOf extends MsgEnvelope {

    /**
     * Ctor.
     * @param from The sender's email address.
     * @param to The target email recipients.
     * @param subj The subject of the email message.
     * @param body The message content.
     * @checkstyle ParameterNameCheck (5 lines)
     * @checkstyle ParameterNumberCheck (5 lines)
     */
    public MsgOf(final String from, final String to, final String subj, final String body) {
        this(
            new TextOf(from),
            new SetOf<>(new TextOf(to)),
            Collections.emptySet(),
            Collections.emptySet(),
            new TextOf(subj),
            new TextOf(body),
            Collections.emptySet()
        );
    }

    /**
     * Ctor.
     * @param from The sender's email address.
     * @param to The target email recipients.
     * @param cc The target email recipients for the `CC` (carbon copy).
     * @param bcc The target email recipients for the `BCC` (blind carbon copy).
     * @param subj The subject of the email message.
     * @param body The message content.
     * @param attachments The message attachments.
     * @checkstyle ParameterNameCheck (10 lines)
     * @checkstyle ParameterNumberCheck (5 lines)
     * @checkstyle AnonInnerLengthCheck (30 lines)
     */
    public MsgOf(
        final Text from, final Set<Text> to, final Set<Text> cc, final Set<Text> bcc,
        final Text subj, final Text body, final Iterable<File> attachments
    ) {
        super(
            new Msg() {
                @Override
                public Text from() {
                    return from;
                }

                @Override
                public Set<Text> to() {
                    return to;
                }

                @Override
                public Set<Text> cc() {
                    return cc;
                }

                @Override
                public Set<Text> bcc() {
                    return bcc;
                }

                @Override
                public Text subject() {
                    return subj;
                }

                @Override
                public Text body() {
                    return body;
                }

                @Override
                public Iterable<File> attachments() {
                    return attachments;
                }
            }
        );
    }
}
