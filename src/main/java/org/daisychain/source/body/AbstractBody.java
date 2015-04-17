package org.daisychain.source.body;

import org.apache.commons.lang.builder.ToStringBuilder;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import org.daisychain.source.AClass;
import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class AbstractBody implements Body {

    public AbstractBody() {
    }

    public static Body abstractBody() {
        return new AbstractBody();
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        writer.append(";");
    }

    public Set<AClass> getImports() {
        return Collections.EMPTY_SET;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}