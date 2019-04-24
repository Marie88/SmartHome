/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package house;

/**
 *
 * @author admin
 */
public abstract class Device {
    
    boolean isON;
    double consumption;
    double energy;
    
    public Device(double consumption,double energy) {
        this.isON = false;
        this.consumption = consumption;
        this.energy = energy;
    }
    
    public abstract void turnON();
    public abstract void  turnOFF();
    public abstract double getConsumption();
    public abstract boolean isON();
    public abstract double getEnergy();
    
}
