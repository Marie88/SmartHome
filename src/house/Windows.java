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
public class Windows extends Device {

    public Windows(){
        super(0,0);
    }
    @Override
    public void turnON() {
       Windows.super.isON = true; //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void turnOFF() {
        Windows.super.isON = false;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean isON() {
        return Windows.super.isON;
    }
      @Override
    public double getConsumption() {
         return 0;
    }

    @Override
    public double getEnergy() {
       return 0;
    }

}
