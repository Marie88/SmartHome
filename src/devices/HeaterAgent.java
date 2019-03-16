/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devices;

import es.upv.dsic.gti_ia.core.ACLMessage;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.SingleAgent;
/**
 *
 * @author admin
 */
public class HeaterAgent extends SingleAgent{
      
    private int currentTemp;
    private boolean active;
    
    public HeaterAgent(AgentID aid,int temp) throws Exception {
        super(aid);
        this.currentTemp = temp;
    }
    
    public void init(){this.active = true;}
    
     public void onMessage(ACLMessage msg){
        //System.out.println("Hi! I'm agent "+this.getName()+" and I've received the message: "+msg.getContent());
        if(Integer.parseInt(msg.getContent())>18){
            System.out.println("Hi! I'm Heater agent and the current temperature is "+this.currentTemp);
        }
        else{
            System.out.println("Hi! I'm Heater agent, current temperature is "+msg.getContent()+" and I turned on the heater");
            //logic to modify current parameter in living room
        }
        
    }
    public void execute(){
	System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
	msg.setSender(this.getAid());
	msg.addReceiver(new AgentID("LivingRoomAgent"));
	msg.setContent("Hi! I'm Heater agent and the current temperature is "+this.currentTemp);
	this.send(msg);
        
            try {
            msg2=this.receiveACLMessage();
            this.send(msg2);
        } catch (InterruptedException ex) {
            
        }
    }
    
    public void finalize(){this.active = false;}
}
