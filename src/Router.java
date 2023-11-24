package src;

import java.util.ArrayList;
import java.util.Vector;

class Router {
    private Vector<String> connections;
    private Semaphore semaphore;

    public Router(int maxConnections) {
        connections = new Vector<>();
        semaphore = new Semaphore(maxConnections);
    }
    public int getConnectionCount() {
        return connections.size();
    }

    public void connect(Device device) throws InterruptedException {
        semaphore.Wait(device);
        connections.add(device.getDeviceName());
        System.out.println("- Connection " + (connections.size()) + ": " + device.getDeviceType() + "( " + device.getDeviceName() + ") Occupied");
    }

    public void disconnect(Device device) {
        connections.remove(device.getDeviceName());
        semaphore.Signal();
        System.out.println("- Connection " + (connections.size() + 1) + ": " + device.getDeviceType() + "( " + device.getDeviceName() + ") Logged out");
    }
}
