package smt.vt.Settings;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by semitro on 19.02.18.
 */
public class Settings {
    private static InetAddress robotIp;
    private static Integer robotPort = 5445;
    private static byte[] move = {'@', 'm', 0, '@'};
    private static byte[] speed_msg = {'@', 's', 0, '@'};

    static {
        try {
            robotIp = InetAddress.getByName("192.168.0.112");
        } catch (UnknownHostException e) {
           // impossible
        }

    }

    public static void setRobotIp(InetAddress ip) {
        Settings.robotIp = ip;
    }

    public static InetAddress getRobotIp() {
        return robotIp;
    }


    public static Integer getRobotPort() {
        return robotPort;
    }

    public static void setRobotPort(Integer port) {
        Settings.robotPort = port;
    }

    public static byte[] getMoveForwardMessage(){
        move[2] = (byte)'w';
        return move;
    }

    public static byte[] getMoveBackMessage(){
        move[2] = (byte)'x';
        return move;
    }
    public static byte[] getMoveRightMessage(){
        move[2] = (byte)'d';
        return move;
    }
    public static byte[] getMoveLeftMessage(){
        move[2] = (byte)'a';
        return move;
    }
    public static byte[] getMoveForwardRightMessage(){
        move[2] = (byte)'e';
        return move;
    }
    public static byte[] getMoveForwardLeftMessage(){
        move[2] = (byte)'q';
        return move;
    }
    public static byte[] getMoveBackLeftMessage(){
        move[2] = (byte)'z';
        return move;
    }
    public static byte[] getMoveBackRightMessage(){
        move[2] = (byte)'c';
        return move;
    }
    public static byte[] getMoveStopMessage(){
        move[2] = (byte)'s';
        return move;
    }

    // value [1; 5]
    public static byte[] getSpeedControlMessage(int value){
        speed_msg[2] = (byte)(value+'0');
        return speed_msg;
    }

}
