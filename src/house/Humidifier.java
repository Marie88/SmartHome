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
public class Humidifier extends Device{

    public Humidifier(){
        super();
    }
    @Override
    public void turnON() {
       Humidifier.super.isON = true; //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void turnOFF() {
        Humidifier.super.isON = false;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

