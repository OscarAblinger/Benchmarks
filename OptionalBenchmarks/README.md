# Benchmarking Java's Optional<T> class

This is the accompanying repository to [my medium article](https://ablingeroscar.medium.com/benchmarking-optional-in-java-2609a7a0c1e9),
where I investigated how much slower `Optional<T>` really is.
I also took a look at the class `OptionalInt`, which a specialised
optional for integers that avoids boxing – and to my surprise it
is actually faster than using `Integer`.
