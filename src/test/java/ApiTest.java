import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * created by guanjian on 2021/3/11 16:13
 */
public class ApiTest {

    public static void main(String[] args) {
        Map map = new HashMap();
        Map map1 = new HashMap();
        map1.put(3,4);

        map.put(1,2);
        map.putAll(map1);
        System.out.println(JSON.toJSONString(map));
    }
}
