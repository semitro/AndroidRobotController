package smt.vt.myapplication;

import smt.vt.Settings.Settings;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPClient {

    private DatagramSocket socket;

    public UDPClient() throws SocketException{
        socket = new DatagramSocket();
    }
    public void sendMsg(byte[] content) throws IOException{
        socket.send(new DatagramPacket(content, content.length, Settings.getRobotIp(), Settings.getRobotPort()));
    }
}
