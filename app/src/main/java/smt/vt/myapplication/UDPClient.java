package smt.vt.myapplication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
    private DatagramSocket socket;
    private InetAddress addr;
    private int port;
    public UDPClient(InetAddress addr, int port) throws SocketException{
        socket = new DatagramSocket();
        this.addr = addr;
        this.port = port;
    }
    public void sendMsg(byte[] content) throws IOException{
        socket.send(new DatagramPacket(content, content.length, addr, port));
    }
}
