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
public class BlindsAgent extends SingleAgent{
    
    private boolean active;
    
     public BlindsAgent(AgentID aid) throws Exception {
        super(aid);
       
    }
    
    public void init(){this.active = true;}
    
    public void onMessage(ACLMessage msg){
        
        double light = Double.parseDouble(msg.getContent().split(" ")[0]);
         
        if (msg.getContent().split(" ")[1].equals("turnON")) {
           
            Room.devices.get("Blinds").turnON();
            System.out.println("Hi! I'm Blinds agent and the current luminosity is " + light + "lux and the blinds have opened");
            
        } else if(msg.getContent().split(" ")[1].equals("turnOFF")) {
           
            Room.devices.get("Blinds").turnOFF();
            System.out.println("Hi! I'm Blinds agent and the current luminosity is " + light + "lux and the blinds have closed");
        }
        else if (msg.getContent().split(" ")[1].equals("ON")){
          
            System.out.println("Hi! I'm Blinds agent and the current luminosity is " + light + "lux and the blinds are open");
        }
        else if (msg.getContent().split(" ")[1].equals("OFF")){
          
             System.out.println("Hi! I'm Blinds agent and the current luminosity is " + light + "lux and the blinds are closed");
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
    public void finalize(){this.active = false;}
}
