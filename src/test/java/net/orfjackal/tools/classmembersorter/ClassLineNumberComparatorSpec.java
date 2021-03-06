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
import static net.orfjackal.tools.classmembersorter.TestData.CLASS_ONE;
import static net.orfjackal.tools.classmembersorter.TestData.CLASS_TWO;
import org.junit.runner.RunWith;

import java.util.Comparator;

/**
 * @author Esko Luontola
 * @since 4.1.2008
 */
@RunWith(JDaveRunner.class)
public class ClassLineNumberComparatorSpec extends Specification<Comparator<Class<?>>> {

    private Comparator<Class<?>> comparator;

    public class WhenTwoInnerClassesAreCompared {

        public Comparator<Class<?>> create() {
            comparator = new ClassLineNumberComparator(TestConfig.getStrategy());
            return comparator;
        }

        public void theFirstOneShouldBeLesser() {
            specify(comparator.compare(CLASS_ONE, CLASS_TWO) < 0);
        }

        public void theSecondOneShouldBeGreater() {
            specify(comparator.compare(CLASS_TWO, CLASS_ONE) > 0);
        }
    }

    public class WhenAnInnerClassIsComparedWithItself {

        public Comparator<Class<?>> create() {
            comparator = new ClassLineNumberComparator(TestConfig.getStrategy());
            return comparator;
        }

        public void itShouldBeEqualToItself() {
            specify(comparator.compare(CLASS_ONE, CLASS_ONE) == 0);
        }
    }
}