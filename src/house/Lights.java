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

    public class Lights extends Device{

    public Lights(double consum,double energy){
        super(consum,energy);
    }
    @Override
    public void turnON() {
       Lights.super.isON = true; 
    }

    @Override
    public void turnOFF() {
        Lights.super.isON = false;
    }
    
    @Override
    public boolean isON() {
        return Lights.super.isON;
    }

    @Override
    public double getConsumption() {
         return Lights.super.consumption;
    }

    @Override
    public double getEnergy() {
       return Lights.super.energy;
    }


}

