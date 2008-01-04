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

import jdave.Specification;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
@RunWith(JDaveRunner.class)
public class ClassLineNumberComparatorSpec extends Specification<ClassLineNumberComparator> {

    private ClassLineNumberComparator comparator;

    public class WhenTwoInnerClassesAreCompared {

        public ClassLineNumberComparator create() {
            comparator = new ClassLineNumberComparator();
            return comparator;
        }

        public void theFirstOneShouldBeLesser() {
            specify(comparator.compare(TestData.CLASS_ONE, TestData.CLASS_TWO) < 0);
        }

        public void theSecondOneShouldBeGreater() {
            specify(comparator.compare(TestData.CLASS_TWO, TestData.CLASS_ONE) > 0);
        }
    }

    public class WhenAnInnerClassIsComparedWithItself {

        public ClassLineNumberComparator create() {
            comparator = new ClassLineNumberComparator();
            return comparator;
        }

        public void itShouldBeEqualToItself() {
            specify(comparator.compare(TestData.CLASS_ONE, TestData.CLASS_ONE) == 0);
        }
    }
}