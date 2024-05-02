package Server;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Tomcat {

    public static void chose(String urlName, String[] params, OutputStream os) throws InvocationTargetException, IllegalAccessException {

        Myrequest ret = new Myrequest();
        Myresponse res = new Myresponse(os);
        // 让ret记录参数hash{key:val}
        for (String str: params) {
            String key = str.split("=")[0];
            String val = str.split("=")[1];
            ret.put(key, val);
        }
        Object[] arr = SearchFile.map.get(urlName); // 获取对应的{类，该类的doGet方法类，该类的doPost的方法类}
        // 获取doGet方法
        Method m = (Method) arr[1];
        // 代理
        m.invoke(arr[0], ret, res);
    }
}
