package org.daisychain.core.generate;

import org.daisychain.source.MutableClass;
import org.daisychain.source.MutableMethod;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class StrictDslGenerator<T> {

    private final DslSpec<T> dslSpec;

    public static <T> StrictDslGenerator generateDslSourceFor(Class<? extends T> clazz) {
        return new StrictDslGenerator<T>(clazz);
    }

    public void appendTo(Writer writer) {
//        new SourceFile(dslSpec.aClass.getPackage(), singletonList(dslClassFor(dslSpec))).appendSource(new IndentingStringWriter(writer));
    }

    private StrictDslGenerator(Class<? extends T> clazz) {
        this.dslSpec = DslSpec.specFor(clazz);
    }

    private static <T> MutableClass dslClassFor(DslSpec<T> dslSpec) {
        final List<MutableMethod> methods = new ArrayList<MutableMethod>();
        final List<MutableClass> chains = new ArrayList<MutableClass>();

        for (Chain<T> chain : dslSpec.chains) {
            methods.add(entryPointMethodFor(chain));
            chains.add(ChainInterfaceGenerator.chainClassFor(chain, dslSpec.aClass));
        }

        return null;

//
//        return DcGeneratedType.aClass(
//                EMPTY_LIST,
//                asList(Modifier.Public),
//                DcClass.generated(dslSpec.aClass.getSimpleName() + "Dsl"),
//                EMPTY_LIST,
//                methods,
//                EMPTY_LIST,
//                chains);

    }

    private static MutableMethod entryPointMethodFor(Chain chain) {
        return null;
//        final String body = new StringBuilder().
//                append("return StrictDsl.create(").
//                append(chain.intro).
//                append("._0.class);").toString();
//
//        return DcGeneratedMethod.aGeneratedMethod(EMPTY_LIST, Modifier.publicStatic,
//                chain.intro,
////                generated(chain.intro + "._0"),
//                EMPTY_LIST,
//                aBody(singleton(concrete(StrictDsl.class)), body));
    }


}
