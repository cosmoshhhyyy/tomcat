package Server;


import java.io.File;

import java.lang.reflect.Method;
import java.util.HashMap;

public class SearchFile {
    public static HashMap<String, Object[]> map = new HashMap<>(); // servlet类名:{类对象，doget方法类, dopost方法类};

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException {

    }
    // 初始化
    public static void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String path = "D:\\桌面\\自学代码练习\\sevlet\\src\\test"; // 项目路径
        traversalFolder(new File(path)); // 递归寻找java文件
    }
    // 遍历文件夹，找到java文件，创建对象，传入map中
    private static void traversalFolder(File folder) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        File[] files = folder.listFiles();
        for (File f: files) {
            if (f.isDirectory()) { // 如果是文件夹，继续遍历，递归
                traversalFolder(f);
            } else { // 若不是，判断是否是java文件
                // 获取路径，反射创建
                String filePath = f.getAbsolutePath(); // D:\桌面\自学代码练习\sevlet\src\test\TestDemo1.java
                String path = filePath.split("src")[1]; // Path= \test\TestDemo1.java
                path = path.substring(1, path.length()); // Path= test\TestDemo1.java
                // 去掉.java后缀，这里我一开始不小心替换成空格，找错找了两小时。。。，我还以为前面错了呢
                path = path.replace(".java", ""); // Path= test\TestDemo1
                path = path.replace("\\", "."); // test.TestDemo1
                // 反射，根据路径，获取此类
                Class<?> c1 = Class.forName(path);
                // 获取注释
                Servlet1 annotation = c1.getAnnotation(Servlet1.class);
                if (annotation != null) {
                    String urlName = annotation.urlName(); // 获取名字
                    // 放入map中
                    map.put(urlName, new Object[]{c1.newInstance(), c1.getDeclaredMethod("doGet", new Class[] {Myrequest.class, Myresponse.class}), c1.getDeclaredMethod("doPost", new Class[] {Myrequest.class, Myresponse.class})});

                }
            }
        }
    }

}
