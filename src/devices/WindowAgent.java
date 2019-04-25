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
   
    private boolean active;
    
    public WindowAgent(AgentID aid) throws Exception {
        super(aid);
       
    }
    public void init(){this.active = true;}
    
     public void onMessage(ACLMessage msg){
        //System.out.println("Hi! I'm agent "+this.getName()+" and I've received the message: "+msg.getContent());
        
        String[] params = msg.getContent().split(",");
        double currentTemp = Double.parseDouble(params[0]);
        double currentHum = Double.parseDouble(params[1]);
        
        if(currentTemp < 25 && currentHum < 60){
            System.out.println("Hi! I'm Window agent and the current temperature is "+currentTemp+" and current humidity is "+currentHum+"%");
        }
        else{
            System.out.println("Hi! I'm Window agent, current temperature is "+currentTemp+" and current humidity is "+currentHum+" % and i opened the window");
        }
      
    }
    
    public void execute(){
	System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");

     
        ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);

	try {
                msg2=this.receiveACLMessage();
                this.send(msg2);
            } catch (InterruptedException ex) {
            
        }
    }
    public void finalize(){this.active = false;}
    
    
}
