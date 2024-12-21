package backend.academy;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

@State(Scope.Thread)
public class ReflectionBenchmark {
    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Supplier<String> methodHandleLambda;

    @Setup
    public void setup() throws Throwable {
        student = new Student("Alexander", "Biryukov");

        // Прямой доступ
        // Прямой доступ к полям через метод Student#name()

        // Рефлексия
        method = Student.class.getMethod("name");

        // MethodHandles
        methodHandle = MethodHandles.lookup().findVirtual(Student.class, "name", MethodType.methodType(String.class));

        methodHandleLambda = (Supplier<String>) LambdaMetafactory.metafactory(
            MethodHandles.lookup(),
            "get",
            MethodType.methodType(Supplier.class, Student.class),
            MethodType.methodType(Object.class),
            methodHandle,
            MethodType.methodType(String.class)
        ).getTarget().invokeExact(student);
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();  // прямой доступ
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);  // использование рефлексии
        bh.consume(name);
    }

    @Benchmark
    public void methodHandle(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invokeExact(student);  // использование MethodHandle
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        String name = methodHandleLambda.get();  // использование LambdaMetafactory
        bh.consume(name);
    }
}
