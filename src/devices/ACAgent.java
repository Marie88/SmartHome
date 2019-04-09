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
public class ACAgent extends SingleAgent{
    
    private double currentTemp;
    private boolean active;
    
    public ACAgent(AgentID aid,int temp) throws Exception {
        super(aid);
        this.currentTemp = temp;
    }
    public void init(){this.active = true;}
    
    public void onMessage(ACLMessage msg){
        //System.out.println("Hi! I'm agent "+this.getName()+" and I've received the message: "+msg.getContent());
        currentTemp = Double.parseDouble(msg.getContent()) ;
        if(currentTemp<25){
            System.out.println("Hi! I'm AC agent and the current temperature is "+this.currentTemp);
        }
        else{
            System.out.println("Hi! I'm AC agent, current temperature is "+this.currentTemp+" and I turned on the AC");
            //logic to modify current parameter in living room
        }
        
    }
    public void execute(){
	System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
         ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
	msg.setSender(this.getAid());
	msg.addReceiver(new AgentID("LivingRoomAgent"));
	msg.setContent("Hi! I'm AC agent and the current temperature is "+this.currentTemp);
	this.send(msg);
        
          try {
            msg2=this.receiveACLMessage();
            this.send(msg2);
        } catch (InterruptedException ex) {
            
        }
    }
    public void finalize(){this.active = false;}
}
