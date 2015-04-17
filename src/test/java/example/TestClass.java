package example;

import org.daisychain.core.annotate.Dsl;
import org.daisychain.core.annotate.Param;

import java.io.InputStream;
import java.util.List;

public class TestClass {

    public final Object depA;
    public final String depB;
    public final InputStream depC;

    @Dsl(intro = "createMeATestClass")
    public TestClass(@Param(term = "thatHasThis") List<Number> depA,
                     @Param(term = "andContains") String depB,
                     @Param(term = "withThis", optional = true) InputStream depC
    ) {
        this.depA = depA;
        this.depB = depB;
        this.depC = depC;
    }

    @Dsl
    public static TestClass aThing(@Param(term = "thatHasThis") List<Number> depA,
                     @Param(term = "andContains") String depB,
                     @Param(term = "withThis", optional = true) InputStream depC
    ) {
        return new TestClass(depA, depB, depC);
    }

}
