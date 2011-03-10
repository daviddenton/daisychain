package org.daisychain.util;

public class Defect extends RuntimeException {
    public Defect(String msg, Throwable e) {
        super(msg, e);
    }
}
