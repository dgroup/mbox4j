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

import java.io.File;
import java.util.Collection;
import java.util.Set;
import org.cactoos.Text;

/**
 * The email message.
 *
 * @since 0.1.0
 * @checkstyle MethodNameCheck (200 lines)
 */
@SuppressWarnings("PMD.ShortMethodName")
public interface Msg {

    /**
     * The sender's email address.
     * @return The email.
     */
    Text from();

    /**
     * The target email recipients (To).
     * @return The email(s).
     */
    Set<Text> to();

    /**
     * The target email recipients for the `CC` (carbon copy).
     * @return The email(s).
     */
    Set<Text> cc();

    /**
     * The target email recipients for the `BCC` (blind carbon copy).
     * @return The email(s).
     */
    Set<Text> bcc();

    /**
     * The subject of the email message.
     * @return The subject.
     */
    Text subject();

    /**
     * The message content.
     * @return The content.
     */
    Text body();

    /**
     * The attachments within the email.
     * @return The attachments.
     */
    Collection<File> attachments();
}
