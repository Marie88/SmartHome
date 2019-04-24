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
public class LightAgent extends SingleAgent{

    private boolean active;
    
    public LightAgent(AgentID aid) throws Exception {
        super(aid);
        this.active = false;
    }
    
     public void onMessage(ACLMessage msg){
   
        double currentLight = Double.parseDouble(msg.getContent());
        //200-1000 lux
        
        if((currentLight>=200 && currentLight<400 && Room.devices.get("Lights").isON())){
            System.out.println("Hi! I'm Light agent and the current light is "+currentLight+"lux and the lights are on");
        }
        else if(currentLight>=400 && Room.devices.get("Lights").isON()){
            System.out.println("Hi! I'm Light agent and the current light is "+currentLight+"lux and I turned off the lights");
            Room.devices.get("Lights").turnOFF();
        }
        else if(currentLight>=400 && !Room.devices.get("Lights").isON()){
             System.out.println("Hi! I'm Light agent and the current light is "+currentLight+"lux and the lights are off");
        }
        else{
            System.out.println("Hi! I'm Light agent, current luminosity is "+currentLight+" lux and I turned on the lights");
            Room.devices.get("Lights").turnON();
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
