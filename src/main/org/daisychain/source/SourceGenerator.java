package org.daisychain.source;

import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;


public interface SourceGenerator {
    void appendSource(IndentingStringWriter writer) throws IOException;
}
