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
public class Heater extends Device{

    public Heater(double consum,double energy){
        super(consum,energy);
    }
    @Override
    public void turnON() {
       Heater.super.isON = true; //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void turnOFF() {
        Heater.super.isON = false;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isON() {
        return Heater.super.isON;
    }
    @Override
    public double getConsumption() {
        return Heater.super.consumption;
    }

    @Override
    public double getEnergy() {
       return Heater.super.energy;
    }

}