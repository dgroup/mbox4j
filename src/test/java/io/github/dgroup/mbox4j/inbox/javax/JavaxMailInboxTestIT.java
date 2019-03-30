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

import io.github.dgroup.mbox4j.GmailProperties;
import io.github.dgroup.mbox4j.Msg;
import io.github.dgroup.mbox4j.Query;
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
    public void read() {
        new Assertion<>(
            "3 emails were read",
            () -> {
                final Mode mode = new ModeOf("first 3 emails");
                final Query query = new QueryOf("pop3s", "INBOX", mode);
                final Func<Folder, Iterable<Msg>> fnc = folder -> new ArrayList<>(
                    new Mapped<>(
                        new ToMsg(), folder.getMessages(1, 3)
                    )
                );
                return new JavaxMailInbox(
                    new GmailProperties(),
                    new Modes(new MapOf<>(new MapEntry<>(mode, fnc)))
                ).read(query);
            },
            Matchers.iterableWithSize(3)
        ).affirm();
    }
}
