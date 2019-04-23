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

    public Device() {
        this.isON = false;
    }
    
    public abstract void turnON();
    public abstract void  turnOFF();
}
