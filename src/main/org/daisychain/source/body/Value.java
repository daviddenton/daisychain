package org.daisychain.source.body;

import org.apache.commons.lang.builder.ToStringBuilder;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import org.daisychain.source.AClass;
import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class Value implements AssignableStatement {

    private final String value;

    private Value(String value) {
        this.value = value;
    }

    public Set<AClass> getImports() {
        return Collections.EMPTY_SET;
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        writer.append(value);
    }

    public static Value value(Object value) {
        return new Value(String.valueOf(value));
    }

    public static Value quotedValue(Object value) {
        return new Value("\""+String.valueOf(value)+"\"");
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }
    
}
