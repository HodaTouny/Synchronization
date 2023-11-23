class Device extends Thread {
    private static int connectionIdCounter = 1;

    private Router router;
    private String name;
    private String type;
    private int connectionId;

    public Device(Router router, String name, String type) {
        this.router = router;
        this.name = name;
        this.type = type;
        this.connectionId = connectionIdCounter++;
    }
    @Override
    public void run() {
        try {
            router.connect(this);
            router.performOnlineActivity(this);
            router.disconnect(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getDeviceName() {
        return name;
    }


    public String getType() {
        return type;
    }

    public int getConnectionId() {
        return connectionId;
    }
}
