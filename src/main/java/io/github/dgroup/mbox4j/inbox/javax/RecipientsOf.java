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

import java.util.Collections;
import java.util.Set;
import javax.mail.Address;
import javax.mail.Message;
import org.cactoos.collection.Mapped;
import org.cactoos.set.SetEnvelope;
import org.cactoos.set.SetOf;

/**
 * The email recipients based on their type for the dedicated message.
 *
 * @since 0.1.0
 */
public final class RecipientsOf extends SetEnvelope<String> {

    /**
     * Ctor.
     * @param type The type of recipients.
     * @param msg The message with recipients.
     */
    public RecipientsOf(final Message.RecipientType type, final Message msg) {
        super(
            () -> {
                final Address[] addresses = msg.getRecipients(type);
                final Set<String> recipients;
                if (addresses == null || addresses.length == 0) {
                    recipients = Collections.emptySet();
                } else {
                    recipients = new SetOf<>(
                        new Mapped<>(Address::toString, addresses)
                    );
                }
                return recipients;
            }
        );
    }
}
