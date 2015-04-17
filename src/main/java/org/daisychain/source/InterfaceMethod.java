package org.daisychain.source;

import org.daisychain.source.body.AbstractBody;

import java.util.Collections;
import java.util.List;

public class InterfaceMethod extends MutableMethod<InterfaceMethod, AbstractBody> {
    public InterfaceMethod(List<Annotation> annotations, AClass returnType, String name) {
        super(new AbstractBody(), annotations, Collections.EMPTY_LIST, returnType, name);
    }
}
