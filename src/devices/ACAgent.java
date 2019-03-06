/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devices;

import es.upv.dsic.gti_ia.core.ACLMessage;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.BaseAgent;

/**
 *
 * @author admin
 */
public class ACAgent extends BaseAgent{
    
    private int currentTemp;
    private boolean active;
    
    public ACAgent(AgentID aid,int temp) throws Exception {
        super(aid);
        this.currentTemp = temp;
        this.active = false;
    }
    public void execute(){
	System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	msg.setSender(this.getAid());
	msg.addReceiver(new AgentID("LivingRoomAgent"));
	msg.setContent("Hi! I'm AC agent and the current temperature is "+this.currentTemp);
	this.send(msg);
    }
}
