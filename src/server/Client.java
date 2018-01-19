package server;

import java.net.InetAddress;

public class Client {
    
    private InetAddress ip;
    private short port;
    private int id;
    private int lastPingTime;
    
    public Client(InetAddress ip, short port, int id, int lastPingTime){
        this.ip = ip;
        this.port = port;
        this.id = id;
        this.lastPingTime = lastPingTime;
    }
    
    public InetAddress getIP(){
        return ip;
    }
    
    public short getPort(){
        return port;
    }
    
    public int getID(){
        return id;
    }
    
    public int getLastPingTime(){
        return lastPingTime;
    }
    
    public void setLastPingTime(int lastPingTime){
        this.lastPingTime = lastPingTime;
    }
    
}
