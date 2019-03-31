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
import java.util.Collection;
import java.util.Set;
import org.cactoos.Text;
import org.cactoos.collection.CollectionOf;

/**
 * The fake implementation of {@link Msg} for unit testing purposes.
 *
 * @since 0.1.0
 * @checkstyle MemberNameCheck (200 lines)
 * @checkstyle ParameterNameCheck (200 lines)
 * @checkstyle ParameterNumberCheck (200 lines)
 */
@SuppressWarnings({"PMD.ShortMethodName", "PMD.AvoidFieldNameMatchingMethodName"})
public final class FakeMsg implements Msg {

    /**
     * The sender's email address.
     */
    private final Text from;

    /**
     * The target email recipients.
     */
    private final Set<Text> to;

    /**
     * The target email recipients for the `CC` (carbon copy).
     */
    private final Set<Text> cc;

    /**
     * The target email recipients for the `BCC` (blind carbon copy).
     */
    private final Set<Text> bcc;

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
    private final Collection<File> attachments;

    /**
     * Ctor.
     * @param from The sender's email address.
     * @param to The target email recipients.
     * @param cc The target email recipients for the `CC` (carbon copy).
     * @param bcc The target email recipients for the `BCC` (blind carbon copy).
     * @param subj The subject of the email message.
     * @param body The message content.
     * @param attachments The message attachments.
     * @checkstyle ParameterNumberCheck (5 lines)
     */
    public FakeMsg(
        final Text from, final Set<Text> to, final Set<Text> cc, final Set<Text> bcc,
        final Text subj, final Text body, final File... attachments
    ) {
        this(from, to, cc, bcc, subj, body, new CollectionOf<>(attachments));
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
     */
    public FakeMsg(
        final Text from, final Set<Text> to, final Set<Text> cc, final Set<Text> bcc,
        final Text subj, final Text body, final Collection<File> attachments
    ) {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subj = subj;
        this.content = body;
        this.attachments = attachments;
    }

    @Override
    public Text from() {
        return this.from;
    }

    @Override
    public Set<Text> to() {
        return this.to;
    }

    @Override
    public Set<Text> cc() {
        return this.cc;
    }

    @Override
    public Set<Text> bcc() {
        return this.bcc;
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
    public Collection<File> attachments() {
        return this.attachments;
    }
}
