/*
 * MIT License
 *
 * Copyright (c) 2023 Karl Zschiebsch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.scvis.parser;

import java.util.List;

import static org.scvis.parser.NameSpace.resolved;

public interface Callable {

    @SuppressWarnings("unchecked")
    static <T> T obj(List<Object> args, int index) throws AccessException {
        if (index >= args.size())
            throw new AccessException("Argument " + index + " is missing", 330);
        try {
            return (T) resolved(args.get(index));
        } catch (ClassCastException e) {
            throw new AccessException(e);
        }
    }

    static Number num(List<Object> args, int index) throws AccessException {
        if (index >= args.size())
            throw new AccessException("Argument " + index + " is missing", 330);
        Object num = resolved(args.get(index));
        if (!(num instanceof Number))
            throw new AccessException("Argument " + index + " is not an instance of number", 340);
        return (Number) num;
    }

    Object call(List<Object> args) throws AccessException;
}
