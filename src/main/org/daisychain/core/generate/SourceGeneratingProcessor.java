package org.daisychain.core.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SourceGeneratingProcessor implements ClasspathWalker.Processor {
    private String rootDirPath;

    public SourceGeneratingProcessor(String rootDirPath) {
        this.rootDirPath = rootDirPath;
    }

    public void process(Class clazz) throws IOException {
        File fileDir = fileFor(dirFor(clazz), clazz);
        final FileWriter writer = new FileWriter(fileDir);
        StrictDslGenerator.generateDslSourceFor(clazz).appendTo(writer);
        writer.close();
    }

    private File fileFor(File dslDir, Class clazz) throws IOException {
        final String fileName = new StringBuilder().append(clazz.getSimpleName()).append("Dsl.java").toString();
        final File file = new File(dslDir, fileName);
        if (!file.createNewFile()) throw new IOException("Can't create DSL file " + file.getAbsolutePath());
        return file;
    }

    private File dirFor(Class clazz) throws IOException {
        File dslDir = new File(new StringBuilder()
                .append(rootDirPath)
                .append(clazz.getPackage().getName().replace('.', File.separatorChar))
                .append(File.separatorChar)
                .toString());
        if (!dslDir.exists()) {
            if (!dslDir.mkdirs()) throw new IOException("Can't create DSL dir " + dslDir.getAbsolutePath());
        }
        return dslDir;
    }
}
