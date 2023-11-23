import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Scanner;

public class Network {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the number of WI-FI Connections?");
        int maxConnections = scanner.nextInt();

        System.out.println("What is the number of devices Clients want to connect?");
        int totalDevices = scanner.nextInt();

        Router router = new Router(maxConnections);

        // Create and start device threads
        Device[] devices = new Device[totalDevices];
        for (int i = 0; i < totalDevices; i++) {
            System.out.println("Enter device name and type (e.g., C1 mobile): ");
            String name = scanner.next();
            String type = scanner.next();
            devices[i] = new Device(router, name, type);
        }

        // Start each device
        for (Device device : devices) {
            device.start();
        }

        // Wait for all threads to finish
        for (Device device : devices) {
            try {
                device.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}
