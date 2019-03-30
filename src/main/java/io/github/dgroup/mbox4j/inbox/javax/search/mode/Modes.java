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

package io.github.dgroup.mbox4j.inbox.javax.search.mode;

import io.github.dgroup.mbox4j.Msg;
import io.github.dgroup.mbox4j.query.mode.Mode;
import java.util.Map;
import javax.mail.Folder;
import org.cactoos.Func;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;

/**
 * The search modes to be used within email folders.
 *
 * @since 0.1.0
 */
public final class Modes extends MapEnvelope<Mode, Func<Folder, Iterable<Msg>>> {

    /**
     * Ctor.
     */
    public Modes() {
        this(
            new MapOf<>(
                new MapEntry<>(new io.github.dgroup.mbox4j.query.mode.All(), new All())
            )
        );
    }

    /**
     * Ctor.
     * @param modes The search modes.
     */
    public Modes(final Map<Mode, Func<Folder, Iterable<Msg>>> modes) {
        super(() -> modes);
    }
}
