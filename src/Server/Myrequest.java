package Server;

import java.util.HashMap;

public class Myrequest {
    private HashMap<String, String> hash = new HashMap<>();

    // 根据传入查找
    public String getVal(String key) {
        return hash.get(key);
    }
    // 传入key:val
    public void put(String key, String val) {
        hash.put(key, val);
    }
}
