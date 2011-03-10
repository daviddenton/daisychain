package org.daisychain.source;

import static org.daisychain.source.Annotation.appendAnnotationsSourceOf;
import static org.daisychain.source.HasImports.ImportExtractor.extractImportsFrom;
import static org.daisychain.source.Modifier.appendModifiersSourceOf;
import org.daisychain.source.body.AssignableStatement;
import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Field extends Instance implements SourceGenerator, HasImports {

    private AssignableStatement value;
    private final List<Annotation> annotations;
    private final List<Modifier> modifiers;

    public Field(List<Annotation> annotations, List<Modifier> modifiers, String name, AClass aClass) {
        super(name, aClass);
        this.modifiers = modifiers;
        this.annotations = annotations;
    }

    public Field withValue(AssignableStatement value) {
        this.value = value;
        return this;
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        appendAnnotationsSourceOf(annotations).to(writer);
        appendModifiersSourceOf(modifiers).to(writer);
        writer.append(aClass.simpleName());
        writer.space();
        reference.appendSource(writer);
        if(value != null) {
            writer.append(" = ");
            value.appendSource(writer);
        }
        writer.append(";");
    }

    protected Set<AClass> additionalImports() {
        return super.getImports();
    }

    public final Set<AClass> getImports() {
        final Set<AClass> imports = extractImportsFrom(annotations);
        imports.addAll(super.getImports());
        return imports;
    }

    protected boolean isAbstract() {
        return modifiers.contains(Modifier.Abstract);
    }
}
