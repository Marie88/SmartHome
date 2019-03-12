/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;
import es.upv.dsic.gti_ia.core.ACLMessage;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.BaseAgent;
import es.upv.dsic.gti_ia.core.SingleAgent;
/**
 *
 * @author admin
 */
public class BasementAgent extends SingleAgent{
    
    private int power;
    private boolean alarm = false;
            
    boolean gotMsg = false;

    public BasementAgent(AgentID aid,int power) throws Exception {
        super(aid);
        this.power = power;
    }
    
     @Override
    public void onMessage(ACLMessage msg){
        System.out.println("Hi! I'm agent "+this.getName()+" and I've received the message: "+msg.getContent());
    }
     public void execute(){
        System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");
        ACLMessage msg = null;
        try {
                msg = this.receiveACLMessage();
            } 
        catch (InterruptedException e) { this.gotMsg = false;   e.printStackTrace(); }
        
        this.gotMsg = true;
        System.out.println("Hi! I'm agent "+this.getName()+" and I've received the message: "+msg.getContent());

    }
    
}
