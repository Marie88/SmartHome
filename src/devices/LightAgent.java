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
public class LightAgent extends BaseAgent{
    
    private int currentLight;
    private boolean active;
    
    public LightAgent(AgentID aid,int light) throws Exception {
        super(aid);
        this.currentLight = light;
        this.active = false;
    }
    
    public void execute(){
	System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	msg.setSender(this.getAid());
	msg.addReceiver(new AgentID("LivingRoomAgent"));
	msg.setContent("Hi! I'm Light agent and the current light is "+this.currentLight+"%");
	this.send(msg);
    }
}
