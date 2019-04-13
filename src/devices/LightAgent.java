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
public class LightAgent extends SingleAgent{

    private boolean active;
    
    public LightAgent(AgentID aid) throws Exception {
        super(aid);
        this.active = false;
    }
    
     public void onMessage(ACLMessage msg){
      
        double currentLight = Integer.parseInt(msg.getContent());
        
        if(currentLight>40){
            System.out.println("Hi! I'm Light agent and the current light is "+currentLight+"%");
        }
        else{
            System.out.println("Hi! I'm Light agent, current luminosity is "+currentLight+" % and I turned on the lights");
            //logic to modify current parameter in living room
        }
        
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
