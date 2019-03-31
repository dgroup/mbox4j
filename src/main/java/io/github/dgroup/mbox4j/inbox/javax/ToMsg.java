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

import io.github.dgroup.mbox4j.Msg;
import io.github.dgroup.mbox4j.msg.MsgOf;
import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import org.cactoos.Func;
import org.cactoos.Scalar;

/**
 * The function to map {@link javax.mail.Message} and {@link Msg}.
 *
 * @since 0.1.0
 * @todo #/DEV Add the support of attachments. For now all emails are
 *  text-based thus we need to define a way how to send the files.
 *  For now <em>Collections.emptySet</em> is used as a stub and should be
 *  removed later.
 */
public final class ToMsg implements Func<Message, Msg> {

    /**
     * The temporal directory for the email attachments.
     */
    private final Scalar<File> tmp;

    /**
     * Ctor.
     */
    public ToMsg() {
        this(() -> new File(".tmp"));
    }

    /**
     * Ctor.
     * @param tmp The temporal directory for the email attachments.
     */
    public ToMsg(final Scalar<File> tmp) {
        this.tmp = tmp;
    }

    @Override
    public Msg apply(final Message msg) throws Exception {
        this.createTemporalDirectoryIfAbsent();
        final Object content = msg.getContent();
        final StringBuilder body = new StringBuilder();
        final Collection<File> attachments = new LinkedList<>();
        if (textPlain(msg)) {
            body.append(content.toString());
        } else if (content instanceof Multipart) {
            final Multipart multipart = (Multipart) content;
            for (final Part part : new PartsOf(multipart)) {
                if (textPlain(part)) {
                    body.append(part.getContent().toString());
                } else if (Part.ATTACHMENT.equals(part.getDisposition())) {
                    attachments.add(new AttachmentOf(part, this.tmp).value());
                }
            }
        }
        return new MsgOf(
            msg.getFrom()[0].toString(),
            new RecipientsOf(Message.RecipientType.TO, msg),
            new RecipientsOf(Message.RecipientType.CC, msg),
            new RecipientsOf(Message.RecipientType.BCC, msg),
            msg.getSubject(),
            body.toString(),
            attachments
        );
    }

    /**
     * Create the temporal directory for the attachments from the email.
     * @throws Exception In case if temporal directory can't be detected
     *  or created.
     */
    private void createTemporalDirectoryIfAbsent() throws Exception {
        final File ftmp = this.tmp.value();
        if (ftmp.exists() && ftmp.isDirectory()) {
            return;
        }
        if (!ftmp.exists() && !ftmp.mkdir()) {
            throw new IllegalArgumentException(
                String.format(
                    "Can't initiate the '%s' as temporal directory for email storing",
                    ftmp.getAbsolutePath()
                )
            );
        }
    }

    /**
     * Ensure that {@link javax.mail.Part} has content `text/plain`.
     * @param part The original part.
     * @return The true when type is matched.
     * @throws MessagingException In the case of connectivity issues.
     * @see Part#getContentType()
     */
    private static boolean textPlain(final Part part) throws MessagingException {
        return part.getContentType().contains("text/plain");
    }
}
