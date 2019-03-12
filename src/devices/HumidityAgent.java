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
public class HumidityAgent extends BaseAgent{
    private int currentHum;
    
    private boolean active;
    
    public HumidityAgent(AgentID aid,int hum) throws Exception {
        super(aid);
        this.currentHum = hum;
    }
    
    public void init(){this.active = true;}
    public void execute(){
	System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	msg.setSender(this.getAid());
	msg.addReceiver(new AgentID("LivingRoomAgent"));
	msg.setContent("Hi! I'm Humidity agent and current humidity is "+this.currentHum+"%");
	this.send(msg);
    }
    public void finalize(){this.active = false;}
}
