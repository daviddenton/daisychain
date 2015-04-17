package example;

import org.daisychain.core.annotate.GeneratedDsl;
import org.daisychain.core.generate.Builder;
import org.daisychain.core.generate.EntryPoint;
import org.daisychain.core.generate.StrictDsl;

import java.io.InputStream;

public class TestClassDsl {

    public static createMeATestClass._0 createMeATestClass() {
        return StrictDsl.create(createMeATestClass._0.class);
    }

    public static create._0 create() {
        return StrictDsl.create(create._0.class);
    }

    @GeneratedDsl(target = TestClass.class)
    public interface createMeATestClass {

        public interface _0 extends EntryPoint<TestClass, _0> {
            _1 thatHasThis(Number _Number);
        }

        public interface _1 {
            _2 andContains(String _String);
        }

        public interface _2 {
            Builder withThis(InputStream _InputStream);
        }
    }

    @GeneratedDsl(target = TestClass.class)
    public interface create {

        public interface _0 extends EntryPoint<TestClass, _0> {
            _1 thatHasThis(Number _Number);
        }

        public interface _1 {
            _2 andContains(String _String);
        }

        public interface _2 {
            Builder withThis(InputStream _InputStream);
        }
    }
}

