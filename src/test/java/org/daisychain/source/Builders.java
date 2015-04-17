package org.daisychain.source;

import static org.daisychain.source.DaisyChain.a;
import static org.daisychain.source.Modifier.privateFinal;
import org.daisychain.source.body.Statement;
import org.daisychain.source.body.StatementedBody;
import static org.daisychain.source.body.Value.value;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.EMPTY_MAP;
import java.util.HashMap;
import java.util.List;

public class Builders {

    public static ExistingClass concreteClass() {
        return ExistingClass.existingClass(String.class, (List<? extends AClass>) EMPTY_LIST);
    }

    public static ExistingClass concreteClassFull() {
        return ExistingClass.existingClass(String.class, asList(concreteClass(), concreteClass()));
    }

    public static StatementedBody aStatementedBodyFull() {
        return new StatementedBody().addStatement(aStatement()).addStatement(aStatement());
    }

    private static Statement aStatement() {
        return aVariableFull().reference;
    }

    public static MutableClass aClassBlank() {
        return a().generatedClass("ABlankClass").extending(concreteClass()).build();
    }

    public static MutableClass aClassFull() {
        return a(privateFinal).generatedClass("AFullClass").
                annotatedBy(anAnnotationBlank()).
                annotatedBy(anAnnotationFull()).
                generifiedBy(concreteClassFull()).
                generifiedBy(concreteClassFull()).
                extending(concreteClass()).
                implementing(concreteClassFull()).
                implementing(concreteClassFull()).build()
                .addField(aFieldBlank())
                .addField(aFieldFull())
                .addInnerClass(aClassBlank())
                .addInnerClass(aClassBlank())
                .addInnerInterface(anInterfaceBlank())
                .addInnerInterface(anInterfaceBlank())
                .addMethod(aMethodBlank())
                .addMethod(aMethodFull());
    }

    public static MutableClass anInterfaceBlank() {
        return new GeneratedInterface(EMPTY_LIST, EMPTY_LIST, "ABlankInterface", EMPTY_LIST, ExistingClass.existingClass(Object.class, (List<? extends AClass>) EMPTY_LIST), EMPTY_LIST);
    }

    public static MutableClass anInterfaceFull() {
        final List<AClass> list = new ArrayList<AClass>() {{add(concreteClassFull()); add(concreteClassFull());}};
        final List<AClass> interfaces = new ArrayList<AClass>() {{add(concreteClassFull()); add(concreteClassFull());}};
        return new GeneratedInterface(
                asList(privateFinal),
                asList(anAnnotationBlank(), anAnnotationFull()),
                "AFullInterface",
                list,
                ExistingClass.existingClass(Object.class, (List<? extends AClass>) EMPTY_LIST),
                interfaces)
                .addInnerClass(aClassBlank())
                .addInnerClass(aClassBlank())
                .addInnerInterface(anInterfaceBlank())
                .addInnerInterface(anInterfaceBlank())
                .addMethod(anInterfaceMethodBlank())
                .addMethod(anInterfaceMethodFull());
    }

    public static Field aFieldBlank() {
        return a().generatedField(aClassBlank(), "aVariable").build();
    }

    public static Field aFieldFull() {
        return a(privateFinal).generatedField(aClassBlank(), "aVariable").
                annotatedBy(anAnnotationBlank()).
                annotatedBy(anAnnotationFull()).
                startingValueOf(value("value")).
                build();
    }

    public static ClassMethod aMethodBlank() {
        return new ClassMethod(EMPTY_LIST, EMPTY_LIST, concreteClass(), "aMethod");
    }

    public static ClassMethod aMethodFull() {
        return new ClassMethod(
                asList(anAnnotationBlank(), anAnnotationFull()),
                asList(privateFinal), concreteClassFull(), "aMethodFull")
                .addException(concreteClassFull()).addException(concreteClassFull())
                .addParameter(aFinalParameter()).addParameter(aFinalParameter())
                .addStatement(aVariableFull().reference)
                .addStatement(aVariableFull().reference);
    }

    public static InterfaceMethod anInterfaceMethodBlank() {
        return new InterfaceMethod(
                EMPTY_LIST,
                concreteClass(),
                "anInterfaceMethodBlank");
    }

    public static InterfaceMethod anInterfaceMethodFull() {
        return new InterfaceMethod(
                EMPTY_LIST,
                concreteClassFull(),
                "anInterfaceMethodFull")
                .addParameter(aFinalParameter()).addParameter(aFinalParameter())
                .addException(concreteClassFull()).addException(concreteClassFull());
    }

    public static Parameter aFinalParameter() {
        return new Parameter(Parameter.ParameterModifier.Final, aVariableFull());
    }

    public static Parameter aNonFinalParameter() {
        return new Parameter(Parameter.ParameterModifier.NonFinal, aVariableFull());
    }

    public static Instance aVariableFull() {
        return new Instance("aVariable", concreteClassFull());
    }

    public static Reference aReference() {
        return new Reference("aReference");
    }

    public static Annotation anAnnotationBlank() {
        return new Annotation(concreteClass(), EMPTY_MAP);
    }

    public static Annotation anAnnotationFull() {
        return new Annotation(concreteClass(), new HashMap<String, String>() {{
            put("name1", "value1");
            put("name2", "value2");
        }});
    }
}
