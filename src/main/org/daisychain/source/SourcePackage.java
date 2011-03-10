package org.daisychain.source;

import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;

public class SourcePackage implements SourceGenerator {
    private final String name;

    public SourcePackage(String name) {
        this.name = name;
    }

    public SourcePackage(Package aPackage) {
        this(aPackage.getName());
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        writer.append("package ").append(name).append(";");
    }
}
