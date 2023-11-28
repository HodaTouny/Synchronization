package src;
class semaphore {
    private int value ;
    private FileWriterHelper outFile = new FileWriterHelper();

    public semaphore(int initial) {
        value = initial;
    }

    public synchronized void Wait(Device device, Connection connectionManager) throws InterruptedException {
        value--;
        if (value < 0) {
            outFile.writeFile("- " + device.getDeviceType() + " (" + device.getDeviceName() + ") Arrived and Waiting");
            wait();
        }
        outFile.writeFile("- " + device.getDeviceType() + " (" + device.getDeviceName() + ") Arrived");
        connectionManager.occupy(device);
        outFile.writeFile("- Connection " + device.getConnectionID() + ": " + device.getDeviceType() + "( " + device.getDeviceName() + ") Occupied");
    }

    public synchronized void Signal(Device device, Connection connectionManager) {
        value++;
        if (value <= 0) {
            notify();
        }
        outFile.writeFile("- Connection " + device.getConnectionID() + ": " + device.getDeviceType() + "( " + device.getDeviceName() + ") Logged out");
        connectionManager.release(device);
    }
}