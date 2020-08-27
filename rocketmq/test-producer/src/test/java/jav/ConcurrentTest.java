package jav;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentTest {
    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
//        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(1);
//        map.put("a", "about");
//        map.put("b", "bob");

        //三种设置初始化容量的方式，得到的sizeCtl是不一致的
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(22);
        getField(map);
        ConcurrentHashMap<String, String> map1 = new ConcurrentHashMap<>(22, 0.75f);
        getField(map1);
        ConcurrentHashMap<String, String> map2 = new ConcurrentHashMap<>(22, 0.75f, 1);
        getField(map2);

    }

    public static void getField(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field sizeCtl = obj.getClass().getDeclaredField("sizeCtl");
        sizeCtl.setAccessible(true);
        System.out.println(sizeCtl.getInt(obj));
    }
}
