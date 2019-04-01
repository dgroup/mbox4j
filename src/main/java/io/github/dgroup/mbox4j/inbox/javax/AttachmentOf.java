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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.mail.MessagingException;
import javax.mail.Part;
import org.cactoos.Scalar;
import org.cactoos.io.InputOf;
import org.cactoos.io.OutputTo;
import org.cactoos.io.TeeInput;
import org.cactoos.scalar.LengthOf;

/**
 * Represents an attachment from {@link javax.mail.Part}.
 *
 * @since 0.1.0
 */
public final class AttachmentOf implements Scalar<File> {

    /**
     * An instance of {@link javax.mail.Part} with attachment.
     */
    private final Part part;

    /**
     * The temporal directory for the email attachments.
     */
    private final Scalar<File> tmp;

    /**
     * Ctor.
     * @param part An instance of {@link javax.mail.Part} with attachment.
     * @param tmp The temporal directory for the email attachments.
     */
    public AttachmentOf(final Part part, final Scalar<File> tmp) {
        this.part = part;
        this.tmp = tmp;
    }

    @Override
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public File value() throws IOException {
        try {
            final Path dest = Paths.get(
                this.tmp.value().getAbsolutePath(), this.part.getFileName()
            );
            try (BufferedInputStream src = this.src(); BufferedOutputStream target = out(dest)) {
                new LengthOf(
                    new TeeInput(
                        new InputOf(src),
                        new OutputTo(target)
                    )
                ).intValue();
            }
            return dest.toFile();
            // @checkstyle IllegalCatchCheck (3 lines)
        } catch (final Exception cause) {
            throw new IOException(cause);
        }
    }

    /**
     * The source stream of the attachment.
     * @return The attachment content.
     * @throws IOException In case of exception during reading procedure.
     * @throws MessagingException In case connectivity exception.
     */
    private BufferedInputStream src() throws IOException, MessagingException {
        return new BufferedInputStream(this.part.getInputStream());
    }

    /**
     * The destination stream of the attachment.
     * @param dest The destination/target path of the attachment.
     * @return The attachment content.
     * @throws IOException In case connectivity exception.
     */
    private static BufferedOutputStream out(final Path dest) throws IOException {
        return new BufferedOutputStream(Files.newOutputStream(dest));
    }
}
