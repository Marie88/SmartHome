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
/**
 *
 * @author admin
 */
public class HeaterAgent extends SingleAgent{
      
   
    private boolean active;
    
    public HeaterAgent(AgentID aid) throws Exception {
        super(aid);
       
    }
    
    public void init(){this.active = true;}
    
     public void onMessage(ACLMessage msg){
        //System.out.println("Hi! I'm agent "+this.getName()+" and I've received the message: "+msg.getContent());
        double temperature = Double.parseDouble(msg.getContent());
        //String season = msg.getContent().split(",")[0];
        
        if(temperature>16 && temperature<25 && Room.devices.get("Heater").isON()){
            System.out.println("Hi! I'm Heater agent and the current temperature is "+temperature+" and the heater is on");
            
        }
        else if (temperature<=16 && !Room.devices.get("Heater").isON()){
            System.out.println("Hi! I'm Heater agent, current temperature is "+temperature+" and I turned on the heater");
            Room.devices.get("Heater").turnON();
        }
        else if (temperature<=16 && Room.devices.get("Heater").isON()){
            System.out.println("Hi! I'm Heater agent, current temperature is "+temperature+" and the heater is turned on");
            Room.devices.get("Heater").turnON();
        }
        else if (temperature >= 25 && Room.devices.get("Heater").isON()){
            System.out.println("Hi! I'm Heater agent, current temperature is "+temperature+" and I turned off the heater");
            Room.devices.get("Heater").turnOFF();
        }
        else{
             System.out.println("Hi! I'm Heater agent, current temperature is "+temperature+" and the heater is turned off");
        }
      
    }
    public void execute(){
	System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");

        ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);

            try {
            msg2=this.receiveACLMessage();
            this.send(msg2);
        } catch (InterruptedException ex) {
            
        }
    }
    
    public void finalize(){this.active = false;}
}
