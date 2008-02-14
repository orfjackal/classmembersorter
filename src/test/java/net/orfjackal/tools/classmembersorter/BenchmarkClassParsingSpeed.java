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

import net.orfjackal.tools.Benchmark;

/**
 * @author Esko Luontola
 * @since 14.2.2008
 */
public class BenchmarkClassParsingSpeed {
    public static void main(String[] args) {
        final Class<?> testClass = ClassMemberSorterSpec.AClassMemberSorter.class;
        Benchmark benchmark = new Benchmark(5, 1000);
        benchmark.runBenchmark("Parse class with ASM", new Runnable() {
            public void run() {
                new AsmLineNumberStrategy().firstLineNumber(testClass, 0);
            }
        });
        benchmark.runBenchmark("Parse class with BCEL", new Runnable() {
            public void run() {
                new BcelLineNumberStrategy().firstLineNumber(testClass, 0);
            }
        });
        benchmark.printResults();
    }
}
