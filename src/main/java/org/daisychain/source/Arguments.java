package org.daisychain.source;

import static org.daisychain.source.HasImports.ImportExtractor.extractImportsFrom;
import org.daisychain.source.body.AssignableStatement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Arguments {
    private final List<Instance> arguments;

    public Arguments(Instance... arguments) {
        this(Arrays.asList(arguments));
    }

    public Arguments(List<Instance> arguments) {
        this.arguments = arguments;
    }

    public int size() {
        return arguments.size();
    }

    public Set<AClass> imports() {
        return extractImportsFrom(arguments);
    }

    public List<AssignableStatement> asStatements() {
        List<AssignableStatement> statements = new ArrayList<AssignableStatement>();
        for (Instance instance : arguments) {
            statements.add(instance.reference);
        }
        return statements;
    }
}
