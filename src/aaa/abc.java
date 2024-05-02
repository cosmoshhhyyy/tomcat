package aaa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class abc {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(80);
        System.out.println("Server started...");

        while (true) {
            Socket socket = ss.accept();
            System.out.println("Client connected.");

            // 缓冲输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 读取客户端发送的HTTP请求
            StringBuilder request = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                request.append(line).append("\r\n");
            }

            // 打印请求内容
            System.out.println("Received request:\n" + request.toString());

            // 要返回的HTTP响应
            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Server: Simple HttpServer/1.0\r\n" +
                    "Date: " + new Date() + "\r\n" +
                    "Content-Type: text/html; charset=utf-8\r\n" +
                    "Content-Length: 69\r\n" +
                    "Connection: close\r\n" +
                    "\r\n" +
                    "<h1>HTML代码</h1><input type=\"text\" / ><input type=\"button\" value=\"按钮\" />";

            // 发送HTTP响应给客户端
            OutputStream os = socket.getOutputStream();
            os.write(httpResponse.getBytes("UTF-8"));
            os.flush();

            // 关闭流和套接字
            os.close();
            br.close();
            socket.close();
        }
    }
}