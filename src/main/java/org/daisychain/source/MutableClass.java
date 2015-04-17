package org.daisychain.source;

import static org.daisychain.source.HasImports.ImportExtractor.extractImportsFrom;
import org.daisychain.source.body.AssignableStatement;
import org.daisychain.source.body.ConstructorCall;
import org.daisychain.source.body.StaticMethodCall;
import org.daisychain.source.util.IndentingStringWriter;
import static org.daisychain.source.util.ListAppender.generateSource;
import static org.daisychain.source.util.ListAppender.loop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class MutableClass<C extends MutableClass, T extends MutableMethod> implements SourceGenerator, AClass<T> {

    private final MethodFinder<T> methodFinder;

    private final AClass superClass;
    private final List<AClass> superInterfaces;
    private final List<Annotation> annotations;
    private final List<Modifier> modifiers;
    private final EntityType entityType;
    private final List<Field> fields = new ArrayList<Field>();
    private final List<AClass> innerClasses = new ArrayList<AClass>();
    private final List<AClass> innerInterfaces = new ArrayList<AClass>();
    private final List<T> methods = new ArrayList<T>();
    private final List<Constructor> constructors = new ArrayList<Constructor>();

    protected final String className;
    protected final List<AClass> generics;

    protected MutableClass(EntityType entityType, List<Modifier> modifiers, List<Annotation> annotations, String className,
                           List<AClass> generics,
                           AClass superClass,
                           List<AClass> superInterfaces) {
        this.className = className;
        this.generics = generics;
        this.annotations = annotations;
        this.modifiers = modifiers;
        this.superInterfaces = superInterfaces;
        this.superClass = superClass;
        this.entityType = entityType;
        this.methodFinder = new MethodFinder(this);
    }


    public MutableClass<C, T> addField(Field field) {
        fields.add(field);
        return this;
    }

    public MutableClass<C, T> addMethod(T method) {
        methods.add(method);
        return this;
    }

    public MutableClass<C, T> addConstructor(Constructor constructor) {
        constructors.add(constructor);
        return this;
    }

    public MutableClass addInnerClass(AClass innerClass) {
        innerClasses.add(innerClass);
        return this;
    }

    public MutableClass addInnerInterface(AClass innerInterface) {
        innerInterfaces.add(innerInterface);
        return this;
    }

    public String simpleName() {
        return className.substring(className.lastIndexOf('.') + 1).replace('$', '.');
    }

    public boolean implementsInterface(Class that) {
        for (AClass superInterface : superInterfaces) {
            if(superInterface.implementsInterface(that)) return true;
        }
        return false;
    }

    public String name() {
        return className.replace('$', '.');
    }

    public boolean isPrimitive() {
        return false;
    }

    public AssignableStatement instantiate(List<AssignableStatement> arguments) {
        return new ConstructorCall(this, methodFinder.constructor(arguments.size()), arguments);
    }

    public Iterable<Constructor> constructors() {
        return constructors;
    }

    public Iterable<T> methods() {
        return methods;
    }

    public AssignableStatement call(final String methodName, List<AssignableStatement> arguments) {
        return new StaticMethodCall(this, methodFinder.method(methodName, arguments.size()), arguments);
    }

    public AssignableStatement call(String methodName) {
        final Arguments arguments = new Arguments();
        return call(methodName, arguments.asStatements());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MutableClass dcClass = (MutableClass) o;

        if (className != null ? !className.equals(dcClass.className) : dcClass.className != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = className != null ? className.hashCode() : 0;
        result = 31 * result + (generics != null ? generics.hashCode() : 0);
        return result;
    }

    public Set<AClass> getImports() {
        final Set<AClass> imports = extractImportsFrom(
                annotations,
                innerClasses,
                innerInterfaces,
                superInterfaces,
                fields,
                methods,
                generics
        );
        imports.addAll(superClass.getImports());
        return imports;
    }

    public int compareTo(MutableClass dcClass) {
        return className.compareTo(dcClass.className);
    }

    public static String toVariableName(String id) {
        return id.replaceAll("-", "");
    }

    public Field fieldNamed(String name) {
        for (Field field : fields) {
            if (field.reference.name.equals(name)) return field;
        }
        throw new IllegalArgumentException("Can't find field named " + name);
    }

    public int compareTo(AClass aClass) {
        return name().compareTo(aClass.name());
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        Annotation.appendAnnotationsSourceOf(annotations).to(writer);
        Modifier.appendModifiersSourceOf(modifiers).to(writer);
        entityType.appendSource(writer);
        writer.space().append(className).space();
//        String className = getImports().isEmpty() && isLowerCase(this.className.charAt(0)) ? this.className : getSimpleName();
//        loop(generics).withPrefix("<").commaSeperated().andForEach(generateSource()).withSuffix(">").to(writer);

        // todo add superclass declaration here

        loop(superInterfaces).withPrefix(entityType.interfaceKeyword + " ").commaSeperated().andForEach(generateSource()).withSuffix(" ").to(writer);

        writer.append("{").indent();
        loop(fields).withPrefix("\n").doubleLineSeperated().andForEach(generateSource()).withSuffix("\n").to(writer);
        loop(constructors).withPrefix("\n").doubleLineSeperated().andForEach(generateSource()).withSuffix("\n").to(writer);
        loop(methods).withPrefix("\n").doubleLineSeperated().andForEach(generateSource()).withSuffix("\n").to(writer);
        loop(innerClasses).withPrefix("\n").doubleLineSeperated().andForEach(generateSource()).withSuffix("\n").to(writer);
        loop(innerInterfaces).withPrefix("\n").doubleLineSeperated().andForEach(generateSource()).to(writer);
        writer.exdent().newLine().append("}");
    }

    public enum EntityType implements SourceGenerator {
    Interface("extends"), Class("implements");

    final String interfaceKeyword;

    private EntityType(String interfaceKeyword) {
        this.interfaceKeyword = interfaceKeyword;
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        writer.append(name().toLowerCase());
    }

}

    public T method(String method, int argumentCount) {
        return methodFinder.method(method, argumentCount);
    }
}
