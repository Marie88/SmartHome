/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import es.upv.dsic.gti_ia.core.ACLMessage;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.SingleAgent;

/**
 *
 * @author admin
 */
public class LivingRoomAgent extends SingleAgent {
    
    private int temp;
    private int light;
    
     boolean gotMsg = false;
    
    public LivingRoomAgent(AgentID aid,int temp,int light) throws Exception {
        super(aid);
        this.temp = temp;
        this.light = light;
    }
    
     public void execute(){
        System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");
        ACLMessage msg = null;
        try {
                msg = this.receiveACLMessage();
            } 
        catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("Hi! I'm agent "+this.getName()+" and I've received the message: "+msg.getContent());

    }
}
