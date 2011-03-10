package org.daisychain.source.util;

import java.io.IOException;

public interface DynamicContent {
    void append(IndentingStringWriter writer) throws IOException;
}