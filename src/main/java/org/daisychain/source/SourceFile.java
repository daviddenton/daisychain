package org.daisychain.source;

import org.apache.commons.lang.builder.ToStringBuilder;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import org.daisychain.source.util.IndentingStringWriter;
import org.daisychain.source.util.ItemContent;
import static org.daisychain.source.util.ListAppender.loop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SourceFile implements SourceGenerator {

    private final SourcePackage aPackage;
    private final MutableClass aClass;

    public SourceFile(
            SourcePackage aPackage,
            MutableClass aClass) {
        this.aPackage = aPackage;
        this.aClass = aClass;
    }

    public String name() {
        return aClass.simpleName()+".java";
    }

    private void appendImports(IndentingStringWriter writer) throws IOException {
        final List<AClass> list = new ArrayList(getImports());
        loop(list).withPrefix("\nimport ").andForEach(new ItemContent<AClass>() {
            public void append(IndentingStringWriter writer, AClass target) throws IOException {
                writer.append(target.name());
            }
        }).seperatedBy(";\nimport ").withSuffix(";\n").to(writer);
    }

    private Set<AClass> getImports() {
        Set<AClass> filtered = new TreeSet<AClass>();
        final Set<AClass> imports = aClass.getImports();
        for (AClass aClass1 : imports) {
            filtered.addAll(aClass1.getImports());
        }
        return filtered;
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        aPackage.appendSource(writer);
        writer.newLine();
        appendImports(writer);
        writer.newLine().newLine();
        aClass.appendSource(writer);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }
    
}
