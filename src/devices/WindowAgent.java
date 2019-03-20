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

public class WindowAgent extends SingleAgent{
    private int currentTemp;
    private int currentHum;
    
    private boolean active;
    
    public WindowAgent(AgentID aid,int temp,int hum) throws Exception {
        super(aid);
        this.currentHum = hum;
        this.currentTemp = temp;
    }
    public void init(){this.active = true;}
    
     public void onMessage(ACLMessage msg){
        //System.out.println("Hi! I'm agent "+this.getName()+" and I've received the message: "+msg.getContent());
        
        String[] params = msg.getContent().split(",");
        currentTemp = Integer.parseInt(params[0]);
        currentHum = Integer.parseInt(params[1]);
        
        if(currentTemp < 25 && currentHum < 50){
            System.out.println("Hi! I'm Window agent and the current temperature is "+this.currentTemp+" and current humidity is "+this.currentHum+"%");
        }
        else{
            System.out.println("Hi! I'm Window agent, current temperature is "+this.currentTemp+" and current humidity is "+this.currentHum+"% and i opened the window");
            //logic to modify current parameter in living room
        }
        
    }
    
    public void execute(){
	System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);

	msg.setSender(this.getAid());
	msg.addReceiver(new AgentID("LivingRoomAgent"));
	msg.setContent("Hi! I'm Window agent and the current temperature is "+this.currentTemp+" and current humidity is "+this.currentHum+"%");
	this.send(msg);
        
            try {
                msg2=this.receiveACLMessage();
                this.send(msg2);
            } catch (InterruptedException ex) {
            
        }
    }
    public void finalize(){this.active = false;}
    
    
}
