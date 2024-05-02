package ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server_Test{
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(80);
        System.out.println("Server started...");

        while (true) {
            Socket socket = ss.accept();
            System.out.println("Client connected.");
            // 缓冲输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 接收传来的协议（字符串）
            String httpstr = ""; // 拼接传过来的协议
            String line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                httpstr += line;
            }
            System.out.println(httpstr);

            // 要返回的协议，学习一下，如何向页面传输的，其实也是字符串
            String http = "HTTP/1.1 200 OK\r\n" +
                    "Server: Simple HttpServer/1.0\r\n" +
                    "Date: " + new Date() + "\r\n" +
                    "Content-Type: text/html; charset=utf-8\r\n" +
                    "Content-Length: 69\r\n" +
                    "Connection: close\r\n" +
                    "\r\n" +
                    "<h1>HTML代码</h1><input type=\"text\" / ><input type=\"button\" value=\"按钮\" />";

            OutputStream os = socket.getOutputStream();
            os.write(http.getBytes("UTF-8"));
            os.flush();

            os.close();
            br.close();
            socket.close();
        }
    }
}