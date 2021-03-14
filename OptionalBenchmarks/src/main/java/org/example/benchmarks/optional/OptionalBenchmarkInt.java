package org.example.benchmarks.optional;

import org.openjdk.jmh.annotations.*;

import java.util.OptionalInt;
import java.util.concurrent.TimeUnit;

@Fork(value = 1, warmups = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Measurement(
        iterations = 3,
        time = 1000,
        timeUnit = TimeUnit.MILLISECONDS
)
@Warmup(
        iterations =  3,
        time = 1000,
        timeUnit = TimeUnit.MILLISECONDS
)
public class OptionalBenchmarkInt {

    public Integer isEvenNullable(int n) {
        if (n % 2 == 0) {
            return n;
        } else {
            return null;
        }
    }

    public OptionalInt isEvenOptional(int n) {
        if (n % 2 == 0) {
            return OptionalInt.of(n);
        } else {
            return OptionalInt.empty();
        }
    }

    @Benchmark
    public Integer nullable_notUsedAfterwards(OptionalBenchmarkState state) {
        return isEvenNullable(state.number);
    }

    @Benchmark
    public OptionalInt optional_notUsedAfterwards(OptionalBenchmarkState state) {
        return isEvenOptional(state.number);
    }

    @Benchmark
    public int nullable_usedAfterwardsAsInt(OptionalBenchmarkState state) {
        Integer evenInt = isEvenNullable(state.number);
        if (evenInt == null) {
            return 0;
        } else {
            return evenInt;
        }
    }

    @Benchmark
    public Integer nullable_usedAfterwardsAsObj(OptionalBenchmarkState state) {
        Integer evenInt = isEvenNullable(state.number);
        if (evenInt == null) {
            return 0;
        } else {
            return evenInt;
        }
    }

    @Benchmark
    public int optional_usedAfterwards(OptionalBenchmarkState state) {
        return isEvenOptional(state.number)
                .orElse(0);
    }

}
