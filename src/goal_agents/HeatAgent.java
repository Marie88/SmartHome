/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goal_agents;

import devices.HeaterAgent;
import es.upv.dsic.gti_ia.core.ACLMessage;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.BaseAgent;
import es.upv.dsic.gti_ia.core.SingleAgent;
import house.Room;


public class HeatAgent extends SingleAgent {

    double externalT;
  
    Room livingroom;
    double heat;
    double cool;
    String season;
    
    public HeatAgent(AgentID aid,Room room,String season) throws Exception {
        super(aid);
        this.livingroom = room;
        this.season = season;
    }

    public void onMessage(ACLMessage msg) {
        
         
        ACLMessage msg2=new ACLMessage(); 
        msg2.setSender(this.getAid());
        
        
        externalT=Double.parseDouble(msg.getContent());
        double interiorT = livingroom.modelTemp(externalT);
        
        if(season.startsWith("sum"))
        {
            msg2.setReceiver(new AgentID("ACAgent"));
             if(Room.devices.get("AC").isON()){
                cool = cool + Room.devices.get("AC").getEnergy();
                livingroom.currentT = interiorT-cool;

            }
            else
            {
                cool-=Room.devices.get("AC").getEnergy();
                livingroom.currentT = interiorT-cool;

            }
            interiorT = livingroom.currentT;
            if(interiorT>16 && interiorT<25 && Room.devices.get("AC").isON()){
               
                msg2.setContent(""+interiorT+" ON");
            }
            else if (interiorT<=16 && Room.devices.get("AC").isON()){
               
                msg2.setContent(""+interiorT+" turnOFF");
            }
            else if (interiorT>=25 && Room.devices.get("AC").isON()){
                
                msg2.setContent(""+interiorT+" ON");
            }
            else if (interiorT >= 25 && !Room.devices.get("AC").isON()){
               
                msg2.setContent(""+interiorT+" turnON");
            }
            else{
                
                msg2.setContent(""+interiorT+" OFF");
            } 

            
        }
        else
        {
            msg2.setReceiver(new AgentID("HeaterAgent"));
            
            if(Room.devices.get("Heater").isON()){
                heat = heat + Room.devices.get("Heater").getEnergy();
                livingroom.currentT = interiorT+heat;

            }
            else
            {
                heat-=Room.devices.get("Heater").getEnergy();
                livingroom.currentT = interiorT+heat;

            }
            interiorT = livingroom.currentT;
            if(interiorT>16 && interiorT<25 && Room.devices.get("Heater").isON()){
               
                msg2.setContent(""+interiorT+" ON");
            }
            else if (interiorT<=16 && !Room.devices.get("Heater").isON()){
               
                msg2.setContent(""+interiorT+" turnON");
            }
            else if (interiorT<=16 && Room.devices.get("Heater").isON()){
                
                msg2.setContent(""+interiorT+" ON");
            }
            else if (interiorT >= 25 && Room.devices.get("Heater").isON()){
               
                msg2.setContent(""+interiorT+" turnOFF");
            }
            else{
                
                msg2.setContent(""+interiorT+" OFF");
            } 
        }
        
       
        
      this.send(msg2);
        
    }
    
     public void execute(){
	System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");
        this.heat = Room.devices.get("Heater").getEnergy();
        this.cool = Room.devices.get("AC").getEnergy();
        
        ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);

            try {
            msg2=this.receiveACLMessage();
            this.send(msg2);
        } catch (InterruptedException ex) {
            
        }
    }

}
