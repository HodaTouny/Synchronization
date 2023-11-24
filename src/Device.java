package src;

class Device extends Thread {
    private String name;
    private String type;
    private Router router;

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



    public void performOnlineActivity(){
        try {
            System.out.println("- Connection " + router.getConnectionCount() + ": " + name + " performs online activity");
            Thread.sleep((long) (Math.random() * 1000));
        }catch (InterruptedException ignored){
            System.out.println("- Connection " + router.getConnectionCount() + ": " + name + "Failed To Preforms online activity");
        }
    }


    @Override
    public void run() {
        try {
            router.connect(this);
            performOnlineActivity();
            router.disconnect(this);
        } catch (InterruptedException ignored) {}
    }
}