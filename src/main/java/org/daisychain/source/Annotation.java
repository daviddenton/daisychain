package org.daisychain.source;

import org.apache.commons.lang.builder.ToStringBuilder;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import org.daisychain.core.annotate.Dsl;
import org.daisychain.core.annotate.Param;
import org.daisychain.source.util.IndentingStringWriter;
import org.daisychain.source.util.ListAppender;
import static org.daisychain.source.util.ListAppender.generateSource;
import static org.daisychain.source.util.ListAppender.loop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Annotation implements SourceGenerator, HasImports {

    private final AClass aClass;
    private final Map<String, String> properties;

    @Dsl(intro = "annotation")
    public Annotation(@Param(term = "ofType") AClass aClass,
                        @Param(term = "withProperties") Map<String, String> properties) {
        this.aClass = aClass;
        this.properties = properties;
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        writer.append("@");
        aClass.appendSource(writer);
        if (!properties.isEmpty()) {
            writer.append("(");
            final ArrayList<Map.Entry<String, String>> entries = new ArrayList<Map.Entry<String, String>>(properties.entrySet());
            for (int i = 0; i < entries.size(); i++) {
                if (i > 0) writer.append(", ");
                writer.append(entries.get(i).getKey()).append(" = ").append(entries.get(i).getValue());
            }
            writer.append(")");
        }
    }

    public Set<AClass> getImports() {
        return aClass.getImports();
    }

    public static ListAppender<Annotation> appendAnnotationsSourceOf(List<Annotation> annotations) {
        return loop(annotations).andForEach(generateSource()).newLineSeperated().withSuffix("\n");
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }
    
}
