import java.util.LinkedList;
import java.util.Queue;

class Router {
    private Semaphore semaphore;
    private Queue<Device> connections;
    private Queue<Device> waitingDevices;

    public Router(int maxConnections) {
        semaphore = new Semaphore(maxConnections);
        connections = new LinkedList<>();
        waitingDevices = new LinkedList<>();
    }

    public void connect(Device device) throws InterruptedException {
        if (connections.size() < semaphore.value) {
            connections.add(device);
            System.out.println("Connection " + device.getConnectionId() + ": " + device.getName() + " login");
        } else {
            waitingDevices.add(device);
            System.out.println(device.getName() + "(" + device.getType() + ") arrived and waiting");
        }
    }

    public void performOnlineActivity(Device device) throws InterruptedException {
        System.out.println("Connection " + device.getConnectionId() + ": " + device.getName() + " performs online activity");
        // Simulate online activity
        Thread.sleep((long) (Math.random() * 1000));
    }

    public void disconnect(Device device) {
        connections.remove(device);
        semaphore.V(); // Release the semaphore

        // Check if there are waiting devices
        if (!waitingDevices.isEmpty()) {
            Device waitingDevice = waitingDevices.poll();
            connections.add(waitingDevice);
            System.out.println("Connection " + waitingDevice.getConnectionId() + ": " + waitingDevice.getName() + " login");
        }

        System.out.println("Connection " + device.getConnectionId() + ": " + device.getName() + " Logged out");
    }
}
