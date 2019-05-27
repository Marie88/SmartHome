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
/**
 *
 * @author admin
 */
public class HumidityAgent extends SingleAgent{
  
    private boolean active;
    
    Room livingroom;
    double humid;
    
    
    public HumidityAgent(AgentID aid,Room room) throws Exception {
        super(aid);
        this.livingroom = room;
        
        
    }
    
    public void init(){this.active = true;}
    
      public void onMessage(ACLMessage msg){
          
          ACLMessage msg2=new ACLMessage(); 
          msg2.setSender(this.getAid());
          msg2.setReceiver(new AgentID("HumidifierAgent"));
          
          double externalH = Double.parseDouble(msg.getContent().split(" ")[0]);
          double externalT = Double.parseDouble(msg.getContent().split(" ")[1]);
          
          double interiorH = livingroom.modelHumidity(externalH, externalT);
 
          livingroom.currentH = interiorH;
        
           if(Room.devices.get("Humidifier").isON())
           {
                humid = humid + Room.devices.get("Humidifier").getEnergy();

                if(interiorH<30)
                   livingroom.currentH = interiorH+humid;
                if(interiorH>60)
                   livingroom.currentH = interiorH-humid;
            }
            else
             {
                if(humid>0){
                    humid-=Room.devices.get("Humidifier").getEnergy();
                }

                livingroom.currentH = interiorH+humid;

            }
          
            interiorH = livingroom.currentH;
          
           if(interiorH>=30 && interiorH <=60 && Room.devices.get("Humidifier").isON())
            {
                msg2.setContent(""+interiorH+" "+"turnOFF");
             
            }
            else if((interiorH<30 || interiorH >60) &&!Room.devices.get("Humidifier").isON())
            {
                msg2.setContent(""+interiorH+" "+"turnON");
              
            } 
            else if (interiorH>=30 && interiorH <=60 && ! Room.devices.get("Humidifier").isON())
            {
                msg2.setContent(""+interiorH+" "+"OFF");
           
            }
            else if (interiorH<30 || interiorH >60 && Room.devices.get("Humidifier").isON())
            {
                msg2.setContent(""+interiorH+" "+"ON");
              
            }
          
           this.send(msg2);

    }
    
    public void execute(){
	System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");
        humid = Room.devices.get("Humidifier").getEnergy();
        ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);

        try {
            msg2=this.receiveACLMessage();
            this.send(msg2);
        } catch (InterruptedException ex) {
            
        }
    }
    public void finalize(){this.active = false;}
}
