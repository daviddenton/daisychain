package example;

import org.daisychain.core.generate.ClasspathWalker;
import org.daisychain.core.generate.DslAnnotatedFilter;
import org.daisychain.core.generate.LogToStdOutProcessor;
import org.daisychain.core.generate.RecursiveClasspathWalker;

import java.io.IOException;

public class DslGeneratorMain {
    public static void main(String[] args) throws IOException {

//        final Method[] methods = DcClass.class.getMethods();
//        for (Method method : methods) {
//            for (Type type : method.getGenericParameterTypes()) {
//                if(ParameterizedType.class.implementsInterface(type.getClass())) {
//                    for (Type type1 : ((ParameterizedType) type).getActualTypeArguments()) {
//                        System.out.println(type1.getClass() instanceof Class);
//                    }
//                }
//            }
//        }
        new RecursiveClasspathWalker("example").forAllClasses(new DslAnnotatedFilter(), new LogToStdOutProcessor());
//        System.out.println(TestClassDsl.create().thatHasThis(1).andContains("").withThis(null).build());
//
//        final List emptyList = Collections.singletonList(ClassBuilderDsl.anInterface().annotatedBy(Collections.EMPTY_LIST).modifiedBy(Collections.EMPTY_LIST).named("hello").extending(Collections.EMPTY_LIST).defining(Collections.EMPTY_LIST).containing(Collections.EMPTY_LIST).build());
//        final JavaSource o = (JavaSource) JavaSourceBuilderDsl.aJavaFile().in(DslGeneratorMain.class.getPackage()).defining(emptyList).build();
//        StringWriter writer = new StringWriter();
//        IndentingStringWriter awriter = new IndentingStringWriter(writer);
//        o.appendTo(awriter);
//        System.out.println(writer);
    }

    private static class NullProcessor implements ClasspathWalker.Processor {
        public void process(Class clazz) throws IOException {
        }
    }
}
