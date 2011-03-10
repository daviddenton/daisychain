package org.daisychain.core.generate;

import org.daisychain.core.annotate.Param;

import java.lang.reflect.Type;

public class ParameterSignature {
    public final int index;
    public final Class clazz;
    public final Type genericType;
    public final Param param;

    public ParameterSignature(int index, Class clazz, Type genericType, Param param) {
        this.index = index;
        this.clazz = clazz;
        this.genericType = genericType;
        this.param = param;
    }
}
