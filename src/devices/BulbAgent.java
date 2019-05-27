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
import java.text.DecimalFormat;

/**
 *
 * @author admin
 */
public class BulbAgent extends SingleAgent{

    private boolean active;
    
    public BulbAgent(AgentID aid) throws Exception {
        super(aid);
        this.active = false;
    }
    
     public void onMessage(ACLMessage msg){
   
        double light = Double.parseDouble(msg.getContent().split(" ")[0]);
      
        if (msg.getContent().split(" ")[1].equals("turnON")) {
           
            Room.devices.get("Lights").turnON();
            System.out.println("Hi! I'm Bulb agent and the current luminosity is " + light + "lux and the bulbs are turned on");
            
        } else if(msg.getContent().split(" ")[1].equals("turnOFF")) {
           
            Room.devices.get("Lights").turnOFF();
            System.out.println("Hi! I'm Bulb agent and the current luminosity is " + light + "lux and the bulbs are turned off");
        }
        else if (msg.getContent().split(" ")[1].equals("ON")){
          
            System.out.println("Hi! I'm Bulb agent and the current luminosity is " + light + "lux and the bulbs are on");
        }
        else if (msg.getContent().split(" ")[1].equals("OFF")){
          
             System.out.println("Hi! I'm Bulb agent and the current luminosity is " + light + "lux and the bulbs are off");
        }
     
    }
    
    public void execute(){
	System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");

        ACLMessage msg2=new ACLMessage(ACLMessage.INFORM);
 	
        try {
            msg2=this.receiveACLMessage();
            this.send(msg2);
        } catch (InterruptedException ex) {
            
        }
        
        
    }
}
