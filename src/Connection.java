package src;

import java.util.HashMap;
import java.util.Map;

class Connection {
    private Map<Integer, Device> map = new HashMap<>();

    public Connection(int connections) {
        for (int i = 1; i <= connections; i++) {
            map.put(i, null);
        }
    }

    public synchronized void occupy(Device device) {
        for (Map.Entry<Integer, Device> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                int connectionID = entry.getKey();
                map.put(connectionID, device);
                device.setConnectionID(connectionID);
                return;
            }
        }
    }

    public synchronized void release(Device device) {
        for (Map.Entry<Integer, Device> entry : map.entrySet()) {
            if (entry.getValue() == device) {
                map.put(entry.getKey(), null);
                return;
            }
        }
    }
}
