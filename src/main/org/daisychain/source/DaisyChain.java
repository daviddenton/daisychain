package org.daisychain.source;

import org.daisychain.source.body.AssignableStatement;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.List;

public class DaisyChain {

    private final Modifier[] modifiers;

    private DaisyChain(Modifier[] modifiers) {
        this.modifiers = modifiers;
    }

    public static DaisyChain a(Modifier... modifiers) {
        return new DaisyChain(modifiers);
    }

    public DcGeneratedClassBuilder generatedClass(String name) {
        return new DcGeneratedClassBuilder(name);
    }

    public DcGeneratedInterfaceBuilder generatedInterface(String name) {
        return new DcGeneratedInterfaceBuilder(name);
    }

    public DcFieldBuilder generatedField(AClass aClass, String name) {
        return new DcFieldBuilder(aClass, name);
    }

    public DcFieldBuilder generatedField(Instance instance) {
        return generatedField(instance.aClass, instance.reference.name);
    }

    public class DcFieldBuilder {
        private final List<Annotation> annotations = new ArrayList<Annotation>();
        private final AClass aClass;
        private final String name;
        private AssignableStatement value;

        private DcFieldBuilder(AClass aClass, String name) {
            this.aClass = aClass;
            this.name = name;
        }

        public DcFieldBuilder annotatedBy(Annotation annotatedBy) {
            annotations.add(annotatedBy);
            return this;
        }

        public DcFieldBuilder startingValueOf(AssignableStatement value) {
            this.value = value;
            return this;
        }

        public Field build() {
            final Field field = new Field(annotations, asList(modifiers), name, aClass);
            if(value != null) {
                field.withValue(value);
            }
            return field;
        }

    }

    public class DcGeneratedClassBuilder {
        private final List<Annotation> annotations = new ArrayList<Annotation>();
        private final List<AClass> interfaces = new ArrayList<AClass>();
        private final List<AClass> generics = new ArrayList<AClass>();
        private final String name;
        private AClass superClass = ExistingClass.existingClass(Object.class, (List<? extends AClass>) Collections.EMPTY_LIST);

        private DcGeneratedClassBuilder(String name) {
            this.name = name;
        }

        public DcGeneratedClassBuilder annotatedBy(Annotation annotatedBy) {
            annotations.add(annotatedBy);
            return this;
        }

        public DcGeneratedClassBuilder implementing(AClass anInterface) {
            interfaces.add(anInterface);
            return this;
        }

        public DcGeneratedClassBuilder extending(AClass superClass) {
            this.superClass = superClass;
            return this;
        }

        public DcGeneratedClassBuilder generifiedBy(AClass generic) {
            generics.add(generic);
            return this;
        }

        public GeneratedClass build() {
            return new GeneratedClass(asList(modifiers), annotations, name, generics, superClass, interfaces);
        }
    }

    public class DcGeneratedInterfaceBuilder {
        private final List<Annotation> annotations = new ArrayList<Annotation>();
        private final List<AClass> interfaces = new ArrayList<AClass>();
        private final List<AClass> generics = new ArrayList<AClass>();
        private final String name;
        private final AClass superClass = ExistingClass.existingClass(Object.class, (List<? extends AClass>) Collections.EMPTY_LIST);

        private DcGeneratedInterfaceBuilder(String name) {
            this.name = name;
        }

        public DcGeneratedInterfaceBuilder annotatedBy(Annotation annotatedBy) {
            annotations.add(annotatedBy);
            return this;
        }

        public DcGeneratedInterfaceBuilder implementing(AClass anInterface) {
            interfaces.add(anInterface);
            return this;
        }

        public DcGeneratedInterfaceBuilder generifiedBy(AClass generic) {
            generics.add(generic);
            return this;
        }

        public GeneratedInterface build() {
            return new GeneratedInterface(asList(modifiers), annotations, name, generics, superClass, interfaces);
        }
    }

    public DcConstructorGeneratedMethodBuilder constructor(MutableClass returnType) {
        return new DcConstructorGeneratedMethodBuilder(returnType);
    }

    public DcConcreteGeneratedMethodBuilder concreteMethodReturning(String name, AClass returnType) {
        return new DcConcreteGeneratedMethodBuilder(name, returnType);
    }

    public DcInterfaceGeneratedMethodBuilder interfaceMethodReturning(String name, AClass returnType) {
        return new DcInterfaceGeneratedMethodBuilder(name, returnType);
    }

    public class DcConcreteGeneratedMethodBuilder {
        private final AClass returnType;
        private final String name;
        private final List<Annotation> annotations = new ArrayList<Annotation>();

        private DcConcreteGeneratedMethodBuilder(String name, AClass returnType) {
            this.name = name;
            this.returnType = returnType;
        }

        public DcConcreteGeneratedMethodBuilder annotatedBy(Annotation annotatedBy) {
            annotations.add(annotatedBy);
            return this;
        }

        public ClassMethod build() {
            return new ClassMethod(annotations, asList(modifiers), returnType, name);
        }
    }

    public class DcConstructorGeneratedMethodBuilder {
        private final MutableClass host;
        private final List<Annotation> annotations = new ArrayList<Annotation>();

        private DcConstructorGeneratedMethodBuilder(MutableClass host) {
            this.host = host;
        }

        public DcConstructorGeneratedMethodBuilder annotatedBy(Annotation annotatedBy) {
            annotations.add(annotatedBy);
            return this;
        }

        public GeneratedConstructor build() {
            return new GeneratedConstructor(annotations, asList(modifiers), host);
        }
    }

    public class DcInterfaceGeneratedMethodBuilder {
        private final AClass returnType;
        private final String name;
        private final List<Annotation> annotations = new ArrayList<Annotation>();

        private DcInterfaceGeneratedMethodBuilder(String name, AClass returnType) {
            this.name = name;
            this.returnType = returnType;
        }

        public DcInterfaceGeneratedMethodBuilder annotatedBy(Annotation annotatedBy) {
            annotations.add(annotatedBy);
            return this;
        }

        public InterfaceMethod build() {
            return new InterfaceMethod(annotations, returnType, name);
        }
    }

}