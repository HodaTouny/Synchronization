package src;

import java.util.HashMap;
import java.util.Map;

class Connection {
    private Map<Integer, Device> map = new HashMap<>();
    public Connection(int connections){
        for(int i=1;i<connections+1;i++){
            map.put(i,null);
        }
    }

    public int occupy(Device device) {
        for (Map.Entry<Integer, Device> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                int connectionID = entry.getKey();
                map.put(connectionID, device);
                return connectionID;
            }
        }
        return -1;
    }

    public void release(Device device) {
        for (Map.Entry<Integer, Device> entry : map.entrySet()) {
            if (entry.getValue() == device) {
                map.put(entry.getKey(), null);
                return;
            }
        }
    }
    public int getConnectionID(Device device) {
        for (Map.Entry<Integer, Device> entry : map.entrySet()) {
            if (entry.getValue() == device) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
