package Server;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException {

        SearchFile.init(); // 初始化，把所有类先实例一个对象，重复利用，算是优化
        ServerSocket ss = new ServerSocket(80);
        Socket socket = ss.accept();

        // 缓冲输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // 接收传来的协议（字符串）
        String httpstr = ""; // 拼接传过来的协议
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            httpstr += line;
        }
        System.out.println(httpstr);

        // 处理字符串
        // 截取我们需要
        String str = httpstr.split("/")[1].split(" ")[0]; // abc?&key1=val1&key2=val2
       // 获取传入的路径（想要请求哪个servlet)
        String urlName = str.split("\\?")[0];
        System.out.println("urlName= " + urlName); // abc

        // 获取参数{key=value}数组
        String paramStr = str.split("\\?")[1];
        System.out.println("paramStr= " + paramStr); // key1=val1&key2=val2
        String[] params = paramStr.split("&"); // {"key1=val1","key2=val2"}
        OutputStream os = socket.getOutputStream();
        // 把参数给tomcat类处理, 这里就简单写，默认get,想调用post,可以自己改写一下,output传入
        Tomcat.chose(urlName, params, os);

        socket.close();
        ss.close();
    }
}
