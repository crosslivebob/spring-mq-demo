package test;

import jdk.nashorn.internal.runtime.Logging;
import org.junit.Test;

import java.util.ArrayList;

public class TestClassLoader {
    @Test
    public void printClassLoaders() throws ClassNotFoundException {
        System.out.println("Classloader of this class:" + TestClassLoader.class.getClassLoader());
        System.out.println("Classloader of Logging:" + Logging.class.getClassLoader());
        System.out.println("Classloader of ArrayList:" + ArrayList.class.getClassLoader());
    }

    @Test
    public void customCloader() throws ClassNotFoundException {
        CustomClassLoader classLoader = new CustomClassLoader();
        Class clz = classLoader.findClass("test.TestClassLoader");
        System.out.println(clz);
    }
}
