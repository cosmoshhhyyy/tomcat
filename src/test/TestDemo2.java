package test;

import Server.Myrequest;
import Server.Myresponse;
import Server.Servlet1;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Servlet1(urlName = "bbb")
public class TestDemo2{
    public void doGet(Myrequest request, Myresponse response) throws IOException {
        // 假设，输出key1和key2的val
        String val1 = request.getVal("key1");
        String val2 = request.getVal("key2");

        System.out.println("testDemo1的 doget 方法   " + "key1= " + val1 + "key2= " + val2);

        // 返回值
        String http = "HTTP/1.1 200 OK\r\n" +
                "Server: Simple HttpServer/1.0\r\n" +
                "Date: Fri, 07 Jul 2023 23:15:09 GMT\r\n" +
                "Content-Type: text/html; charset=utf-8\r\n" +
                "Content-Length: 162\r\n" +
                "Connection: keep-alive\r\n" +
                "\r\n" +
                "<h1>" + "这是TestDemo2" + "</h1><input type=\"text\" / ><input type=\"button\"  value=\"按钮\"    />";
        response.getWriter().write(http.getBytes("UTF-8"));
        // 清理缓冲
        response.getWriter().flush();
        response.getWriter().close();
    }

    public void doPost(Myrequest request, Myresponse response) {
        // 假设，输出key1和key2的val
        String val1 = request.getVal("key1");
        String val2 = request.getVal("key2");

        System.out.println("testDemo1的 dopost 方法   " + "key1= " + val1 + "key2= " + val2);
    }
}