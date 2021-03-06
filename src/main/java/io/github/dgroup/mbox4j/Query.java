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

package io.github.dgroup.mbox4j;

import io.github.dgroup.mbox4j.query.Mode;

/**
 * The query for email search procedure..
 *
 * @since 0.1.0
 * @todo #/DEV Use {@link org.cactoos.Text} instead raw String for manipulation
 *  with text.
 */
public interface Query {

    /**
     * The email connection protocol.
     * Supported values are:
     *  - <em>pop3</em>
     *  - <em>pop3s</em>
     *  - <em>imap</em>
     *  - <em>imaps</em>.
     * @return The protocol
     */
    String protocol();

    /**
     * The root folder for search.
     * @return The root folder.
     */
    String folder();

    /**
     * The type of the search mode within email folder.
     * @return The mode.
     * @see io.github.dgroup.mbox4j.query.mode
     */
    Mode mode();

}
