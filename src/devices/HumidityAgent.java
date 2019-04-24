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
public class HumidityAgent extends SingleAgent{
  
    private boolean active;
    
    public HumidityAgent(AgentID aid) throws Exception {
        super(aid);
        
    }
    
    public void init(){this.active = true;}
    
      public void onMessage(ACLMessage msg){
      
        double currentHum = Double.parseDouble(msg.getContent().split(",")[1]);
        if(!(currentHum<30 || currentHum >60)){
            System.out.println("Hi! I'm Humidity agent and the current humidity is "+currentHum);
        }
        else{
            System.out.println("Hi! I'm Humidity agent, current humidity is "+currentHum+" % and I turned on the humidifier");
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
