package org.daisychain.source.body;

import org.daisychain.source.Instance;
import org.daisychain.source.Reference;
import org.daisychain.source.Method;
import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;
import java.util.List;

public class MethodCall extends AbstractMethodCall {
    private final Reference reference;

    public MethodCall(Reference reference, Method method, List<AssignableStatement> arguments) {
        super(method, arguments);
        this.reference = reference;
    }

    @Override
    public void appendSource(IndentingStringWriter writer) throws IOException {
        reference.appendSource(writer);
        writer.append(".");
        super.appendSource(writer);
    }
}
