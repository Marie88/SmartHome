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
public class Blinds extends Device{
    
    public Blinds(){
        super();
    }
    @Override
    public void turnON() {
       Blinds.super.isON = true; //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void turnOFF() {
        Blinds.super.isON = false;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
