package src;

import java.util.Scanner;
import java.util.Vector;

public class Network {
    static Vector<Device> devices = new Vector<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the number of WI-FI Connections?");
        int maxConnections = scanner.nextInt();

        System.out.println("What is the number of devices Clients want to connect?");
        int totalDevices = scanner.nextInt();

        Router router = new Router(maxConnections);

        for (int i = 1; i <= totalDevices; i++) {
            System.out.println("Enter the details for Device " + i + " (format: Name Type): ");
            String name = scanner.next();
            String type = scanner.next();
            devices.add(new Device(name, type, router));
        }

        for (Device device : devices) {
            device.start();
        }
        try {
            for (Device device : devices) {
                device.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Join interrupted");
        }

        scanner.close();
    }
}
