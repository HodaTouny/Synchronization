package src;

class Device extends Thread {
    private String name;
    private String type;
    private Router router;
    private int ConnectionID;

    public void setConnectionID(int con) {
        ConnectionID = con;
    }

    public int getConnectionID() {
        return ConnectionID;
    }

    public Device(String name, String type, Router router) {
        this.name = name;
        this.type = type;
        this.router = router;
    }

    public String getDeviceName() {
        return name;
    }

    public String getDeviceType() {
        return type;
    }

    @Override
    public void run() {
        try {
            router.connect(this);
            router.performOnlineActivity(this);
            router.disconnect(this);
        } catch (InterruptedException ignored) {
        }
    }
}
