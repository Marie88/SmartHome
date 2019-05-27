/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goal_agents;

import es.upv.dsic.gti_ia.core.ACLMessage;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.SingleAgent;
import house.Room;
import java.text.DecimalFormat;

/**
 *
 * @author admin
 */
public class LightAgent extends SingleAgent{

    private boolean active;
    Room livingroom;
    
    public LightAgent(AgentID aid,Room room) throws Exception {
        super(aid);
        this.active = false;
        this.livingroom = room;
    }
    
     public void onMessage(ACLMessage msg){
   
         
          ACLMessage msg2=new ACLMessage(); 
          msg2.setSender(this.getAid());
       
          double externalL = Double.parseDouble(msg.getContent());
       
          
          double interiorL = livingroom.modelLuminosity(externalL);
       
          livingroom.currentL = interiorL;
          
          if(Room.devices.get("Lights").isON()){
              livingroom.currentL = interiorL + Room.devices.get("Lights").getEnergy();
          }
          else if(Room.devices.get("Blinds").isON()){
              livingroom.currentL = interiorL - Room.devices.get("Blinds").getEnergy();
          }
        
          interiorL = livingroom.currentL;
          
          
          
          if(interiorL >= 1500 && !Room.devices.get("Blinds").isON())
          {
              msg2.setReceiver(new AgentID("BlindsAgent"));
              msg2.setContent(""+interiorL+" "+"turnON");
              
          }else if (interiorL< 1000 && Room.devices.get("Blinds").isON()){
              msg2.setReceiver(new AgentID("BlindsAgent"));
              msg2.setContent(""+interiorL+" "+"turnOFF");
          }
          else if (interiorL <= 1500 && interiorL >= 1000 && Room.devices.get("Blinds").isON()){
              msg2.setReceiver(new AgentID("BlindsAgent"));
              msg2.setContent(""+interiorL+" "+"ON");
          }
          else if (interiorL < 1000 && !Room.devices.get("Blinds").isON()){
              msg2.setReceiver(new AgentID("BlindsAgent"));
              msg2.setContent(""+interiorL+" "+"OFF");
          }
          
          
          if((interiorL>=200 && interiorL<500 && Room.devices.get("Lights").isON()))
          {
              msg2.setReceiver(new AgentID("BulbAgent"));
              msg2.setContent(""+interiorL+" "+"ON");

          }
        else if(interiorL>=500 && Room.devices.get("Lights").isON()){
          
            msg2.setReceiver(new AgentID("BulbAgent"));
            msg2.setContent(""+interiorL+" "+"turnOFF");
        }
        else if(interiorL>=500 && !Room.devices.get("Lights").isON()){
           
            msg2.setReceiver(new AgentID("BulbAgent"));
            msg2.setContent(""+interiorL+" "+"OFF");
        }
        else if(interiorL <=200 && !Room.devices.get("Lights").isON()){
          
            msg2.setReceiver(new AgentID("BulbAgent"));
            msg2.setContent(""+interiorL+" "+"turnON");
        }
   
       this.send(msg2);
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
