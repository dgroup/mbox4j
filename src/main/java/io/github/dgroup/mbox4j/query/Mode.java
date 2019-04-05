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

package io.github.dgroup.mbox4j.query;

/**
 * The supported email search modes.
 *
 * @since 0.1.0
 */
public interface Mode {

    /**
     * Find all emails within particular folder.
     */
    String ALL = "all";

    /**
     * Find all recent emails within particular folder.
     */
    String RECENT = "recent";

    /**
     * Find the range of emails based on their indexes
     *  within particular folder.
     * The indexes starts from <em>1</em>.
     */
    String RANGE = "range";

    /**
     * Find all unread emails within particular folder.
     */
    String UNREAD = "unread";

    /**
     * The type of the search mode.
     * @return The name.
     */
    String name();

    /**
     * The argument's value within the search mode.
     * @param name The name of the argument.
     * @param fallback The default value in case if argument is absent.
     * @return The value.
     */
    String argument(String name, String fallback);
}
