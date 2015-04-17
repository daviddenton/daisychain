package org.daisychain.source.util;

import java.io.IOException;
import java.io.Writer;

public class IndentingStringWriter {
    private final Writer writer;
    private final int tabSize;
    private int tabCount;

    public IndentingStringWriter(Writer writer, int tabSize) {
        this.writer = writer;
        this.tabSize = tabSize;
    }

    public IndentingStringWriter(Writer writer) {
        this(writer, 4);
    }

    public IndentingStringWriter append(String str) throws IOException {
        String totalIndent = "";
        for (int i = 0; i < tabCount; i++) totalIndent += "    ";
        writer.append(str.replaceAll("\n", "\n" + totalIndent));
        return this;
    }

    public IndentingStringWriter indent() {
        tabCount++;
        return this;
    }

    public IndentingStringWriter exdent() {
        if (tabCount > 0) tabCount--;
        return this;
    }

    public IndentingStringWriter space() throws IOException {
        return append(" ");
    }

    public IndentingStringWriter newLine() throws IOException {
        return append("\n");
    }

    @Override
    public String toString() {
        return writer.toString();
    }

    public void close() throws IOException {
        writer.close();
    }
}
