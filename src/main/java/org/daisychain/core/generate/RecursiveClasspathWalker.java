package org.daisychain.core.generate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class RecursiveClasspathWalker implements ClasspathWalker {

    private final String aPackage;

    public RecursiveClasspathWalker(String pkg) {
        if (pkg.endsWith("*")) pkg = pkg.substring(0, pkg.length() - 1);
        if (pkg.endsWith("/")) pkg = pkg.substring(0, pkg.length() - 1);
        this.aPackage = pkg.replace('.', File.separatorChar);
    }

    public void forAllClasses(ClasspathWalker.Filter filter, ClasspathWalker.Processor processor) throws IOException {
        Enumeration<URL> resources = getClassLoader().getResources(aPackage + File.separatorChar);
        while (resources.hasMoreElements()) {
            String path = sanitizeURLForWindows(resources.nextElement().getFile());
            if (path != null && path.trim().length() > 0) {
                ClasspathWalker classpathWalker = isJARPath(path) ? new JARClasspathWalker(path) : new DirectoryClasspathWalker(path);
                classpathWalker.forAllClasses(filter, processor);
            }
        }
    }

    private ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private boolean isJARPath(String path) {
        return path.indexOf("!") > 0 & path.endsWith(".jar");
    }

    private void process(Filter filter, Processor processor, String className) throws IOException {
        if (className.startsWith(aPackage) && className.endsWith(".class")) {
            try {
                final Class<?> aClass = Class.forName(className.substring(0, className.indexOf(".class")).replace('/', '.'));
                if(filter.accept(aClass)) processor.process(aClass);
            } catch (ClassNotFoundException e) {
                // ignore
            }
        }
    }

    private class JARClasspathWalker implements ClasspathWalker {

        private final String path;

        public JARClasspathWalker(String path) {
            this.path = path;
        }

        public void forAllClasses(Filter filter, Processor processor) throws IOException {
            String jarPath = path.substring(0, path.indexOf("!")).substring(path.indexOf(":") + 1);
            JarInputStream jarFile = new JarInputStream(new FileInputStream(jarPath));
            JarEntry jarEntry;
            while ((jarEntry = jarFile.getNextJarEntry()) != null) {
                process(filter, processor, jarEntry.getName());
            }
        }
    }

    private class DirectoryClasspathWalker implements ClasspathWalker {
        private final String path;

        public DirectoryClasspathWalker(String path) {
            this.path = path;
        }

        public void forAllClasses(Filter filter, Processor processor) throws IOException {
            File directory = new File(path);
            if (directory.exists()) {
                for (String dirFilePath : directory.list()) {
                    if (new File(directory, dirFilePath).isFile()) {
                        process(filter, processor, path.substring(path.indexOf(aPackage)) + dirFilePath);
                    } else {
                        new DirectoryClasspathWalker(path + dirFilePath + "/").forAllClasses(filter, processor);
                    }
                }
            }
        }
    }

    private String sanitizeURLForWindows(String path) {
        if (path.indexOf("%20") > 0) path = path.replaceAll("%20", " "); // Encodes
        if (path.indexOf(":") >= 0 && path.startsWith("/")) path = path.substring(1);
        return path;
    }

}
