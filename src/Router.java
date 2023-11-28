package src;

class Router {
    private semaphore Semaphore;
    private Connection connectionManager;
    private FileWriterHelper outFile = new FileWriterHelper();

    public Router(int maxConnections) {
        Semaphore = new semaphore(maxConnections);
        connectionManager = new Connection(maxConnections);
    }

    public void connect(Device device) throws InterruptedException {
        Semaphore.Wait(device, connectionManager);
    }

    public synchronized void performOnlineActivity(Device device) {
        int ConnectionId = device.getConnectionID();
        try {
            outFile.writeFile("- Connection " + ConnectionId + ": " + device.getDeviceName() + " Login");
            outFile.writeFile("- Connection " + ConnectionId + ": " + device.getDeviceName() + " performs online activity");
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException ignored) {
            outFile.writeFile("- Connection " + ConnectionId + ": " + device.getDeviceName() + " Failed To Preforms online activity");
        }
    }

    public synchronized void disconnect(Device device) {
        Semaphore.Signal(device, connectionManager);
    }
}