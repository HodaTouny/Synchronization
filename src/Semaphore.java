package src;

class Semaphore {
    private int value = 0;

    public Semaphore(int initial) {
        value = initial;
    }

    public synchronized void Wait(Device device) throws InterruptedException {
        value--;
        if (value < 0) {
            System.out.println("- " + device.getDeviceType() + " (" + device.getDeviceName() + ") Arrived and Waiting");
            wait();
        } else {
            System.out.println("- " + device.getDeviceType() + " (" + device.getDeviceName() + ") Arrived");
        }
    }

    public synchronized void Signal() {
        value++;
        if (value <= 0) {
            notify();
        }
    }
}
