# Wi-Fi Router Simulation

This repository contains a Java project that simulates a Wi-Fi router managing a limited number of devices using threading and semaphores. The router is designed to limit the number of open connections, allowing only N connections at any given time.

## Problem Definition

Routers can be configured to restrict the number of concurrent connections. In this simulation:

- The Wi-Fi starts with zero connected devices.
- Clients log in and, if they can be served (reach the internet), perform the following activities:
    - Connect
    - Perform online activity
    - Log out

    Note: Random waiting times between these actions simulate real-world scenarios.

- If all connections are occupied, new clients wait until a connection becomes available.

- After a client logs out, a waiting client (if any) will connect to the internet.

## Implementation

The simulation is implemented using Java, threading, and semaphores. The code ensures that the router manages connections safely and effectively.
