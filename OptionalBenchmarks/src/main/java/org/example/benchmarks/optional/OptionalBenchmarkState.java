package org.example.benchmarks.optional;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;

@org.openjdk.jmh.annotations.State(Scope.Benchmark)
public class OptionalBenchmarkState {
    @Param({"1", "2"})
    public int number;
}
