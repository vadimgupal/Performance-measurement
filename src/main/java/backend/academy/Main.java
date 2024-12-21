package backend.academy;

import java.util.concurrent.TimeUnit;
import lombok.experimental.UtilityClass;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@SuppressWarnings("MagicNumber")
@UtilityClass
public class Main {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.minutes(2))
            .build();

        new Runner(options).run();
    }
}
