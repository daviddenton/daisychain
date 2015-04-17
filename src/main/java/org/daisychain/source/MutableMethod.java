package org.daisychain.source;

import org.apache.commons.lang.builder.ToStringBuilder;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.daisychain.source.HasImports.ImportExtractor.extractImportsFrom;
import org.daisychain.source.body.AssignableStatement;
import org.daisychain.source.body.Body;
import org.daisychain.source.body.MethodCall;
import org.daisychain.source.body.StaticMethodCall;
import org.daisychain.source.util.IndentingStringWriter;
import org.daisychain.source.util.ItemContent;
import static org.daisychain.source.util.ListAppender.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public abstract class MutableMethod<MM extends MutableMethod, B extends Body> implements SourceGenerator, Method {

    private final List<Parameter> parameters = new ArrayList<Parameter>();
    private final List<AClass> exceptions = new ArrayList<AClass>();
    private final AClass returnType;
    private final String name;
    private final List<Annotation> annotations;
    private final List<Modifier> modifiers;
    final B body;

    protected MutableMethod(B body, List<Annotation> annotations, List<Modifier> modifiers, AClass returnType, String name) {
        this.body = body;
        this.returnType = returnType;
        this.name = name;
        this.annotations = annotations;
        this.modifiers = modifiers;
    }

    public Set<AClass> getImports() {
        Set<AClass> imports = extractImportsFrom(annotations, parameters, exceptions);
        imports.addAll(returnType.getImports());
        return imports;
    }

    void appendReturnType(IndentingStringWriter writer) throws IOException {
        returnType.appendSource(writer);
        writer.append(" ");
    }

    public AClass returnType() {
        return returnType;
    }

    public AssignableStatement call(final List<AssignableStatement> arguments) {
        return new AssignableStatement() {
            public Set<AClass> getImports() {
                return extractImportsFrom(arguments);
            }

            public void appendSource(IndentingStringWriter writer) throws IOException {
                loopWrapped(arguments).withPrefix(name + "(").commaSeperated().andForEach(new ItemContent<AssignableStatement>() {
                    public void append(IndentingStringWriter writer, AssignableStatement target) throws IOException {
                        target.appendSource(writer);
                    }
                }).withSuffix(")").to(writer);
            }
        };
    }


    public void appendSource(IndentingStringWriter writer) throws IOException {
        Annotation.appendAnnotationsSourceOf(annotations).to(writer);
        Modifier.appendModifiersSourceOf(modifiers).to(writer);
        appendReturnType(writer);
        writer.append(name);
        loopWrapped(parameters).withPrefix("(").andForEach(generateSource()).commaSeperated().withSuffix(")").to(writer);
        loop(exceptions).withPrefix(" throws ").commaSeperated().andForEach(generateSource()).to(writer);
        body.appendSource(writer);
    }


    public MM addException(AClass exception) {
        exceptions.add(exception);
        return (MM) this;
    }

    public MM addParameter(Parameter parameter) {
        parameters.add(parameter);
        return (MM) this;
    }

    public String name() {
        return name;
    }

    public List<Annotation> annotations() {
        return annotations;
    }

    public List<Parameter> parameters() {
        return parameters;
    }

    public List<AClass> exceptions() {
        return exceptions;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }


}
