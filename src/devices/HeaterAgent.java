/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devices;

import es.upv.dsic.gti_ia.core.ACLMessage;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.SingleAgent;
import house.Room;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class HeaterAgent extends SingleAgent {

    private boolean active;
 

    public HeaterAgent(AgentID aid) throws Exception {
        super(aid);

    }

    public void init() {
        this.active = true;
    }
  
    public void onMessage(ACLMessage msg) {
        
        
        
        double temperature = Double.parseDouble(msg.getContent().split(" ")[0]);
       
        
        if (msg.getContent().split(" ")[1].equals("turnON")) {
           
            Room.devices.get("Heater").turnON();
            System.out.println("Hi! I'm Heater agent and the current temperature is " + temperature + " and the heater is turned on");
            
        } else if(msg.getContent().split(" ")[1].equals("turnOFF")) {
           
            Room.devices.get("Heater").turnOFF();
            System.out.println("Hi! I'm Heater agent and the current temperature is " + temperature + " and the heater is turned off");
        }
        else if (msg.getContent().split(" ")[1].equals("ON")){
          
            System.out.println("Hi! I'm Heater agent and the current temperature is " + temperature + " and the heater is on");
        }
        else if (msg.getContent().split(" ")[1].equals("OFF")){
          
             System.out.println("Hi! I'm Heater agent and the current temperature is " + temperature + " and the heater is off");
        }
        

    }

    public void execute() {
        System.out.println("Hi! I'm agent " + this.getName() + " and I start my execution");
       
        
        ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);

        try {
            msg2 = this.receiveACLMessage();
            this.send(msg2);
        } catch (InterruptedException ex) {

        }
    }

    public void finalize() {
        this.active = false;
    }
}
