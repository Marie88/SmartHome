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
public class AC extends Device{

    public AC(double consum,double energy){
        super(consum,energy);
    }
    @Override
    public void turnON() {
       AC.super.isON = true; //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void turnOFF() {
        AC.super.isON = false;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean isON() {
        return AC.super.isON;
    }
     @Override
    public double getConsumption() {
         return AC.super.consumption;
    }

    @Override
    public double getEnergy() {
       return AC.super.energy;
    }

}
