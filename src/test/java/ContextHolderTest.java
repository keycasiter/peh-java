import com.alibaba.fastjson.JSON;
import com.github.peh.context.ContextHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * created by guanjian on 2021/2/25 17:39
 */
public class ContextHolderTest {

    private final static String key = "aaa";
    private final static String value = "bbb";

    public static void put() {
        ContextHolder.put(key, value);
        System.out.println(JSON.toJSONString(ContextHolder.get(key)));
    }

    public static void clear() {
        ContextHolder.put(key, value);
        System.out.println(JSON.toJSONString(ContextHolder.get(key)));
        ContextHolder.clear();
        System.out.println(JSON.toJSONString(ContextHolder.get(key)));
    }

    public static void putAll() {
        ContextHolder.put(key, value);
        System.out.println(JSON.toJSONString(ContextHolder.get(key)));

        Map map = new HashMap();
        map.put("cccc", "dddd");
        ContextHolder.putAll(map);
        System.out.println(JSON.toJSONString(ContextHolder.get(key)));
    }

    public static void getOrDefault() {
        ContextHolder.put(key, value);
        System.out.println(ContextHolder.getOrDefault(key, "ssss"));
        System.out.println(ContextHolder.getOrDefault("xxxx", "ssss"));
    }

    public static void containsKey() {
        ContextHolder.put(key, value);
        System.out.println(ContextHolder.containsKey(key));
        System.out.println(ContextHolder.containsKey("sss"));
    }

    public static void containsValue() {
        ContextHolder.put(key, value);
        System.out.println(ContextHolder.containsValue(value));
        System.out.println(ContextHolder.containsValue("ss"));
    }

    public static void match() {
        ContextHolder.put(key, value);
        System.out.println(ContextHolder.match(key,value));
    }

    public static void main(String[] args) {
//        put();
//        clear();
//        putAll();
//        getOrDefault();
//        containsKey();
//        containsValue();
        match();
    }

}
