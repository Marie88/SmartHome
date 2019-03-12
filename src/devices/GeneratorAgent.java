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
public class GeneratorAgent extends BaseAgent{
    private int currentPower;
    private boolean active;
    
    public GeneratorAgent(AgentID aid,int power) throws Exception {
        super(aid);
        this.currentPower = power;
    }
    
    public void init(){this.active = true;}
    public void execute(){
	System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	msg.setSender(this.getAid());
	msg.addReceiver(new AgentID("BasementAgent"));
	msg.setContent("Hi! I'm Generator agent and current power is at "+this.currentPower+"%");
	this.send(msg);
    }
    public void finalize(){this.active = false;}
    
}
