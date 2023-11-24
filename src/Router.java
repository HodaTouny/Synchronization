package src;

import java.util.Vector;


class Router {
    private Vector<String> connections;
    private Semaphore semaphore;
    private int currentConnections = 0;
    private Object lock = new Object();

    public Router(int maxConnections) {
        connections = new Vector<>();
        semaphore = new Semaphore(maxConnections);
    }

    public int getConnectionCount() {
        synchronized (lock) {
            return currentConnections;
        }
    }

    public void connect(Device device) throws InterruptedException {
        semaphore.Wait(device);
        synchronized (lock) {
            currentConnections++;
            connections.add(device.getDeviceName());
            int connectionId = currentConnections;
            System.out.println("- Connection " + connectionId + ": " + device.getDeviceType() + "( " + device.getDeviceName() + ") Occupied");
        }
    }

    public synchronized void performOnlineActivity(Device device){
        int ConnectionId = getConnectionCount();
        try {
            System.out.println("- Connection " + ConnectionId + ": " + device.getDeviceName() + " Login");
            System.out.println("- Connection " + ConnectionId + ": " + device.getDeviceName()  + " performs online activity");
            Thread.sleep((long) (Math.random() * 1000));
        }catch (InterruptedException ignored){
            System.out.println("- Connection " + ConnectionId+ ": " +device.getDeviceName() + "Failed To Preforms online activity");
        }
    }


    public void disconnect(Device device) {
        synchronized (lock) {
            connections.remove(device.getDeviceName());
            int connectionId = currentConnections;
            currentConnections--;
            semaphore.Signal();
            System.out.println("- Connection " + connectionId + ": " + device.getDeviceType() + "( " + device.getDeviceName() + ") Logged out");
        }
    }
}