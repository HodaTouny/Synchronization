package src;

class Semaphore {
    protected int value = 0;

    protected Semaphore() {
        value = 0;
    }

    protected Semaphore(int initial) {
        value = initial;
    }

    public synchronized void Wait(Device device) throws InterruptedException {
        value--;
        if (value < 0) {
            wait();
            System.out.println("- "+ device.getDeviceType() + " (" + device.getDeviceName() + ") Arrived and Waiting");
            return;
        }
        System.out.println("- "+ device.getDeviceType() + " (" + device.getDeviceName() + ") Arrived");
    }

    public synchronized void Signal() {
        value++;
        if (value <= 0) {
            notify();
        }
    }
}