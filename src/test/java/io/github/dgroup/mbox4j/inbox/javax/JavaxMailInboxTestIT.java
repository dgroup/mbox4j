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

import io.github.dgroup.mbox4j.EmailException;
import io.github.dgroup.mbox4j.Msg;
import io.github.dgroup.mbox4j.Query;
import io.github.dgroup.mbox4j.YandexIncomingSmtpProperties;
import io.github.dgroup.mbox4j.inbox.javax.search.mode.Modes;
import io.github.dgroup.mbox4j.query.QueryOf;
import io.github.dgroup.mbox4j.query.mode.Mode;
import io.github.dgroup.mbox4j.query.mode.ModeOf;
import java.util.ArrayList;
import javax.mail.Folder;
import org.cactoos.Func;
import org.cactoos.collection.Mapped;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasValues;

/**
 * Test case for {@link JavaxMailInbox}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 * @todo #/DEV Enable test once the SMTP server is UP and available for reading.
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class JavaxMailInboxTestIT {

    @Test
    public void size() {
        new Assertion<>(
            "3 emails were read",
            () -> this.read(3),
            Matchers.iterableWithSize(3)
        ).affirm();
    }

    @Test
    public void subject() {
        new Assertion<>(
            "3 emails have expected subject",
            () -> new Mapped<>(
                msg -> msg.subject().asString(),
                this.read(3)
            ),
            new HasValues<>(
                "The first email",
                "The second email",
                "The third email"
            )
        ).affirm();
    }

    /**
     * Read range of emails from the dedicated SMTP server.
     * @param quantity The quantity of messages to be fetched from SMTP server.
     * @return The emails.
     * @throws EmailException Шn case of connectivity issues.
     */
    private Iterable<Msg> read(final int quantity) throws EmailException {
        final Mode mode = new ModeOf("first several emails");
        final Query query = new QueryOf("imaps", "INBOX", mode);
        final Func<Folder, Iterable<Msg>> fnc = folder -> new ArrayList<>(
            new Mapped<>(
                new ToMsg(), folder.getMessages(1, quantity)
            )
        );
        return new JavaxMailInbox(
            new YandexIncomingSmtpProperties(),
            new Modes(new MapOf<>(new MapEntry<>(mode, fnc)))
        ).read(query);
    }
}
