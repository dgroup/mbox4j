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

import io.github.dgroup.mbox4j.Query;
import java.util.Objects;

/**
 * The query for email search procedure.
 *
 * @since 0.1.0
 */
public final class QueryOf implements Query {

    /**
     * The email connection protocol.
     */
    private final String pcl;

    /**
     * The root folder for search.
     */
    private final String fld;

    /**
     * The type of the search mode within email folder.
     */
    private final Mode mde;

    /**
     * Ctor.
     * @param protocol The email connection protocol.
     *  Supported values are:
     *  - <em>pop3</em>
     *  - <em>pop3s</em>
     *  - <em>imap</em>
     *  - <em>imaps</em>.
     * @param folder The root folder for search.
     * @param mode The type of the search mode within email folder.
     */
    public QueryOf(final String protocol, final String folder, final Mode mode) {
        this.pcl = protocol;
        this.fld = folder;
        this.mde = mode;
    }

    @Override
    public String protocol() {
        return this.pcl;
    }

    @Override
    public String folder() {
        return this.fld;
    }

    @Override
    public Mode mode() {
        return this.mde;
    }

    @Override
    @SuppressWarnings("PMD.OnlyOneReturn")
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Query)) {
            return false;
        }
        final Query that = (Query) obj;
        return Objects.equals(this.protocol(), that.protocol())
            && Objects.equals(this.folder(), that.folder())
            && Objects.equals(this.mode().name(), that.mode().name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.protocol(), this.folder(), this.mode().name());
    }

    @Override
    public String toString() {
        return String.format(
            "protocol=%s, folder=%s, mode=%s",
            this.protocol(), this.folder(), this.mode().name()
        );
    }
}
