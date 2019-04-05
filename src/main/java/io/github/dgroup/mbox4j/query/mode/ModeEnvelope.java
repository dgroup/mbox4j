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

package io.github.dgroup.mbox4j.query.mode;

import io.github.dgroup.mbox4j.query.Arg;
import io.github.dgroup.mbox4j.query.Mode;
import java.util.Map;
import org.cactoos.iterable.IterableOf;
import org.cactoos.map.MapOf;

/**
 * The envelope for {@link Mode}.
 *
 * @since 0.1.0
 */
public class ModeEnvelope implements Mode {

    /**
     * The name of the search mode.
     */
    private final String label;

    /**
     * The search arguments.
     */
    private final Map<String, String> arg;

    /**
     * Ctor.
     * @param mode The name of the search mode.
     * @param args The search arguments.
     */
    public ModeEnvelope(final String mode, final Arg... args) {
        this(mode, new MapOf<>(Arg::name, Arg::value, new IterableOf<>(args)));
    }

    /**
     * Ctor.
     * @param mode The name of the search mode.
     * @param args The search arguments.
     */
    public ModeEnvelope(final String mode, final Map<String, String> args) {
        this.label = mode;
        this.arg = args;
    }

    @Override
    public final String name() {
        return this.label;
    }

    @Override
    public final String argument(final String name, final String fallback) {
        return this.arg.getOrDefault(name, fallback);
    }

}
