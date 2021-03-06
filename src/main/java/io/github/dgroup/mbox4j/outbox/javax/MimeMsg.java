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

package io.github.dgroup.mbox4j.outbox.javax;

import io.github.dgroup.mbox4j.Msg;
import java.io.File;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.cactoos.BiFunc;

/**
 * The function to evaluate the {@link javax.mail.internet.MimeMessage}.
 *  from {@link Msg}.
 *
 * @since 0.1.0
 */
public final class MimeMsg implements BiFunc<Session, Msg, Message> {

    @Override
    public Message apply(final Session session, final Msg msg) throws Exception {
        final Message email = new MimeMessage(session);
        email.setFrom(new InternetAddress(msg.from().asString()));
        email.setRecipients(Message.RecipientType.TO, new Addresses(msg.to()).value());
        email.setRecipients(Message.RecipientType.CC, new Addresses(msg.cc()).value());
        email.setRecipients(Message.RecipientType.BCC, new Addresses(msg.bcc()).value());
        email.setSubject(msg.subject().asString());
        if (msg.attachments().isEmpty()) {
            email.setText(msg.body().asString());
        } else {
            final Multipart multipart = new MimeMultipart();
            final MimeBodyPart text = new MimeBodyPart();
            text.setText(msg.body().asString());
            multipart.addBodyPart(text);
            for (final File attachment : msg.attachments()) {
                final MimeBodyPart part = new MimeBodyPart();
                final DataSource source = new FileDataSource(attachment);
                part.setDataHandler(new DataHandler(source));
                part.setFileName(attachment.getName());
                multipart.addBodyPart(part);
            }
            email.setContent(multipart);
        }
        return email;
    }
}
