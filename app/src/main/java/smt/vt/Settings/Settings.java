package smt.vt.Settings;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by semitro on 19.02.18.
 */
public class Settings {
    private static InetAddress robotIp;
    private static Integer robotPort = 5445;
    private static byte[] move_forward = new byte[]{'@','m','w','@'};

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

    public  static byte[] getMoveForwardMessage(){
        return move_forward;
    }
}
