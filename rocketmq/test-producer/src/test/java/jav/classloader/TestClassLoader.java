package jav.classloader;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * 测试类加载器
 */
public class TestClassLoader {

    @Test
    public void test() throws IllegalAccessException, InstantiationException {
        String className = "com.leyou.search.pojo.SearchRequest";
        //加载类
        TestLoader loader = new TestLoader();
        Class clazz = loader.findClass(className);
        Object object = clazz.newInstance();
        System.out.println(object);
    }

}

class TestLoader extends ClassLoader {

    @SneakyThrows
    public Class findClass(String name) {
        String[] split = name.split("\\.");
        byte[] bytes = loadClassData(split[split.length - 1]);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassData(String name) throws Exception {
        // load the class data from the connection
        String filePath = "D:\\develop\\repository\\my_code\\leyou\\leyou-search\\target\\classes\\com\\leyou\\search\\pojo\\" + name + ".class";
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] bytes = new byte[(int) file.length()];
        fileInputStream.read(bytes);

        return bytes;
    }
}
