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
import java.util.Objects;
import java.util.Set;
import org.cactoos.Text;
import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;

/**
 * The envelope for {@link Msg}.
 *
 * @since 0.1.0
 */
@SuppressWarnings("PMD.ShortMethodName")
public class MsgEnvelope implements Msg {

    /**
     * Original message.
     */
    private final Msg origin;

    /**
     * Ctor.
     * @param origin The original message.
     */
    public MsgEnvelope(final Msg origin) {
        this.origin = origin;
    }

    @Override
    public final Text from() {
        return this.origin.from();
    }

    @Override
    public final Set<Text> to() {
        return this.origin.to();
    }

    @Override
    public final Set<Text> cc() {
        return this.origin.cc();
    }

    @Override
    public final Set<Text> bcc() {
        return this.origin.bcc();
    }

    @Override
    public final Text subject() {
        return this.origin.subject();
    }

    @Override
    public final Text body() {
        return this.origin.body();
    }

    @Override
    public final Collection<File> attachments() {
        return this.origin.attachments();
    }

    @Override
    @SuppressWarnings("PMD.OnlyOneReturn")
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Msg)) {
            return false;
        }
        final Msg that = (Msg) obj;
        return Objects.equals(this.from(), that.from())
            && Objects.equals(this.to(), that.to())
            && Objects.equals(this.cc(), that.cc())
            && Objects.equals(this.bcc(), that.bcc())
            && Objects.equals(this.subject(), that.subject())
            && Objects.equals(this.body(), that.body())
            && Objects.equals(this.attachments(), that.attachments());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(
            this.origin.subject(), this.origin.body(),
            this.origin.from(), this.origin.to(), this.origin.bcc()
        );
    }

    @Override
    public final String toString() {
        return String.format(
            "from=%s, to=%s, cc=%s, bcc=%s, subject=%s, body=%s, attachments=%s",
            new UncheckedText(this.from()).asString(),
            new Joined(new TextOf(", "), this.to()),
            new Joined(new TextOf(", "), this.cc()),
            new Joined(new TextOf(", "), this.bcc()),
            new UncheckedText(this.subject()).asString(),
            new UncheckedText(this.body()).asString(),
            this.attachments()
        );
    }
}
