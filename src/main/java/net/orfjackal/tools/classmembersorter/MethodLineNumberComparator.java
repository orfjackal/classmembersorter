/*
 * Class Member Sorter
 * Copyright (c) 2008 Esko Luontola, www.orfjackal.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.orfjackal.tools.classmembersorter;

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * Sorts methods according to the order in which they have been declared in the source code.
 *
 * @author Esko Luontola
 * @since 4.1.2008
 */
public class MethodLineNumberComparator implements Comparator<Method> {

    private final LineNumberStrategy strategy;

    public MethodLineNumberComparator(LineNumberStrategy strategy) {
        if (strategy == null) {
            throw new NullPointerException("strategy is null");
        }
        this.strategy = strategy;
    }

    public int compare(Method m1, Method m2) {
        if (sameClass(m1, m2)) {
            int line1 = firstLineNumber(m1);
            int line2 = firstLineNumber(m2);
            return line1 - line2;
        } else if (parentClass(m1, m2)) {
            return 1;
        } else if (parentClass(m2, m1)) {
            return -1;
        } else {
            return alphabeticalOrder(m1, m2);
        }
    }

    private int firstLineNumber(Method method) {
        return strategy.firstLineNumber(method, 0);
    }

    private static boolean sameClass(Method m1, Method m2) {
        return m1.getDeclaringClass().equals(m2.getDeclaringClass());
    }

    private static boolean parentClass(Method parent, Method child) {
        return parent.getDeclaringClass().isAssignableFrom(child.getDeclaringClass());
    }

    private static int alphabeticalOrder(Method m1, Method m2) {
        return m1.getDeclaringClass().getName().compareTo(m2.getDeclaringClass().getName());
    }
}
