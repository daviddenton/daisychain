package org.daisychain.core.generate;

import org.daisychain.source.*;

import java.util.ArrayList;
import java.util.Collection;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
import java.util.Iterator;
import java.util.List;

public class ChainInterfaceGenerator {


    private static List<MutableClass> linkMethods(Chain chain, MutableClass builtClass) {
        List<MutableClass> linkMethodInterfaces = new ArrayList<MutableClass>();

//        List<DcClass> interfaceList = singletonList(DcClass.concrete(EntryPoint.class, asList(builtClass, DcClass.generated(chain.intro + "._0"))));
        for (Iterator<ParameterSignature> it = chain.getParameterSignatures().listIterator(); it.hasNext();) {
//            ParameterSignature parameterSignature = it.next();
//            final List<DcGeneratedMethod> methods = parameterMethods(parameterSignature, it.hasNext() ? DcClass.generated("_" + parameterSignature.index + 1, EMPTY_LIST) : DcClass.concrete(Builder.class, singletonList(builtClass)));
//            linkMethodInterfaces.add(DcGeneratedType.anInterface(
//                    EMPTY_LIST,
//                    asList(Modifier.Public),
//                    DcClass.generated("_" + parameterSignature.index),
//                    interfaceList,
//                    methods,
//                    EMPTY_LIST));
//
//            interfaceList = EMPTY_LIST;
        }

        return linkMethodInterfaces;
    }

    private static List<MutableMethod> parameterMethods(ParameterSignature parameterSignature, MutableClass nextParameter) {
//        return singletonList(interfaceMethod(EMPTY_LIST, parameterSignature.param.term(),
//                nextParameter,
//                asList(toParameterBuilder(parameterSignature)),
//                EMPTY_LIST));
        return null;
        
    }

    private static Instance toParameterBuilder(ParameterSignature parameterSignature) {
        final List<MutableClass> generics = Collection.class.isAssignableFrom(parameterSignature.clazz) ? singletonList(ExistingClass.existingClass(parameterSignature.clazz, (List<? extends AClass>) EMPTY_LIST)) : EMPTY_LIST;
//        return aVariable("_" + parameterSignature.clazz.getSimpleName(), DcClass.concrete(parameterSignature.clazz, generics));
        return null;
    }

    static MutableClass chainClassFor(Chain chain, Class aClass) {
        return null;
//        DcClass builtClass = DcClass.concrete(aClass);
//        return DcGeneratedType.anInterface(
//                singletonList(new DcAnnotation(DcClass.concrete(GeneratedDsl.class), singletonMap("target", builtClass.getSimpleName()+".class"))),
//                singletonList(Modifier.Public),
//                DcClass.generated(chain.intro),
//                EMPTY_LIST,
//                EMPTY_LIST,
//                linkMethods(chain, builtClass));
    }
}
