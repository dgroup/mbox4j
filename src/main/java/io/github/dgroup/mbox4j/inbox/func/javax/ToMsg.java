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

package io.github.dgroup.mbox4j.inbox.func.javax;

import io.github.dgroup.mbox4j.Msg;
import io.github.dgroup.mbox4j.msg.MsgOf;
import java.util.Collections;
import java.util.Set;
import javax.mail.Message;
import javax.mail.MessagingException;
import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.collection.Mapped;
import org.cactoos.set.SetOf;
import org.cactoos.text.TextOf;

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

    @Override
    public Msg apply(final Message msg) throws Exception {
        return new MsgOf(
            () -> msg.getFrom()[0].toString(),
            recipients(Message.RecipientType.TO, msg),
            recipients(Message.RecipientType.CC, msg),
            recipients(Message.RecipientType.BCC, msg),
            msg::getSubject,
            () -> msg.getContent().toString(),
            Collections.emptySet()
        );
    }

    /**
     * Fetch the recipients based on their type from the message.
     * @param type The type of recipients.
     * @param msg The message with recipients.
     * @return The recipients.
     * @throws MessagingException In case if recipients can't be retrieved.
     */
    private static Set<Text> recipients(final Message.RecipientType type, final Message msg)
        throws MessagingException {
        return new SetOf<>(
            new Mapped<>(
                recpnt -> new TextOf(recpnt.toString()),
                msg.getRecipients(type)
            )
        );
    }
}
