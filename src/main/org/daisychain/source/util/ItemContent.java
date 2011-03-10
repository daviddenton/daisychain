package org.daisychain.source.util;

import java.io.IOException;

public interface ItemContent<T> {
    void append(IndentingStringWriter writer, T target) throws IOException;
}
