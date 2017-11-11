package org.programirame.utilities;

import java.io.File;

public class FileUtils {

    public static File getFile(Class<?> aClass, String fileName) {
        ClassLoader classLoader = aClass.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}
