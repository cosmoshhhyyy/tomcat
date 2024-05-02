package Server;

import java.io.OutputStream;

public class Myresponse {
    private OutputStream os; // 输出流

    // 构造方法，把tcp输出流构造进来，为了后面我们输出
    public Myresponse(OutputStream os) {
        this.os = os;
    }
    // 获取输出流
    public OutputStream getWriter() {
        return this.os;
    }
}
