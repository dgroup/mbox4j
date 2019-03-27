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
import java.util.Set;
import org.cactoos.Text;

/**
 * The fake implementation of {@link Msg} for unit testing purposes.
 *
 * @since 0.1.0
 * @checkstyle MemberNameCheck (200 lines)
 */
@SuppressWarnings("PMD.ShortMethodName")
public final class FakeMsg implements Msg {

    /**
     * The sender's email address.
     */
    private final Text frm;

    /**
     * The target email recipients.
     */
    private final Set<Text> recipients;

    /**
     * The target email recipients for the `CC` (carbon copy).
     */
    private final Set<Text> copy;

    /**
     * The target email recipients for the `BCC` (blind carbon copy).
     */
    private final Set<Text> bcopy;

    /**
     * The subject of the email message.
     */
    private final Text subj;

    /**
     * The message content.
     */
    private final Text content;

    /**
     * The message attachments.
     */
    private final Iterable<File> attachs;

    /**
     * Ctor.
     * @param frm The sender's email address.
     * @param recipients The target email recipients.
     * @param copy The target email recipients for the `CC` (carbon copy).
     * @param bcopy The target email recipients for the `BCC` (blind carbon copy).
     * @param subj The subject of the email message.
     * @param content The message content.
     * @param attachs The message attachments.
     * @checkstyle ParameterNumberCheck (5 lines)
     */
    public FakeMsg(
        final Text frm, final Set<Text> recipients, final Set<Text> copy, final Set<Text> bcopy,
        final Text subj, final Text content, final Iterable<File> attachs
    ) {
        this.frm = frm;
        this.recipients = recipients;
        this.copy = copy;
        this.bcopy = bcopy;
        this.subj = subj;
        this.content = content;
        this.attachs = attachs;
    }

    @Override
    public Text from() {
        return this.frm;
    }

    @Override
    public Set<Text> to() {
        return this.recipients;
    }

    @Override
    public Set<Text> cc() {
        return this.copy;
    }

    @Override
    public Set<Text> bcc() {
        return this.bcopy;
    }

    @Override
    public Text subject() {
        return this.subj;
    }

    @Override
    public Text body() {
        return this.content;
    }

    @Override
    public Iterable<File> attachments() {
        return this.attachs;
    }

}
