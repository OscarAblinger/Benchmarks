package org.example.benchmarks.optional;

import org.openjdk.jmh.annotations.*;

import java.util.Optional;
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
public class OptionalBenchmarkString {

    public String isEvenNullable(int n) {
        if (n % 2 == 0) {
            return "even";
        } else {
            return null;
        }
    }

    public Optional<String> isEvenOptional(int n) {
        if (n % 2 == 0) {
            return Optional.of("even");
        } else {
            return Optional.empty();
        }
    }

    @Benchmark
    public String nullable_notUsedAfterwards(OptionalBenchmarkState state) {
        return isEvenNullable(state.number);
    }

    @Benchmark
    public Optional<String> optional_notUsedAfterwards(OptionalBenchmarkState state) {
        return isEvenOptional(state.number);
    }

    @Benchmark
    public String nullable_usedAfterwards_nc(OptionalBenchmarkState state) {
        String evenStr = isEvenNullable(state.number);
        if (evenStr == null) {
            return "ODD";
        } else {
            return evenStr.toUpperCase();
        }
    }

    @Benchmark
    public String optional_usedAfterwards_nc(OptionalBenchmarkState state) {
        return isEvenOptional(state.number)
                .map(String::toUpperCase)
                .orElse("ODD");
    }

    @Benchmark
    public String nullable_usedAfterwards_c(OptionalBenchmarkState state) {
        String evenStr = isEvenNullable(state.number);
        if (evenStr == null) {
            return "ODD";
        } else {
            return evenStr.toUpperCase() + state.number;
        }
    }

    @Benchmark
    public String optional_usedAfterwards_c(OptionalBenchmarkState state) {
        return isEvenOptional(state.number)
                .map(str -> str.toUpperCase() + state.number)
                .orElse("ODD");
    }

}
