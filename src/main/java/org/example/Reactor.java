package org.example;

import java.util.HashMap;

public class Reactor {
    double burnup;
    String reactorClass;
    double electricalCapacity;
    double firstLoad;
    double kpd;
    double lifeTime;
    double terminalCapacity;
    String fileType;


    public Reactor(double burnup, String reactorClass, double electricalCapacity, double firstLoad, double kpd, double lifeTime, double terminalCapacity, String fileType) {
        this.burnup = burnup;
        this.reactorClass = reactorClass;
        this.electricalCapacity = electricalCapacity;
        this.firstLoad = firstLoad;
        this.kpd = kpd;
        this.lifeTime = lifeTime;
        this.terminalCapacity = terminalCapacity;
        this.fileType = fileType;
    }
}
