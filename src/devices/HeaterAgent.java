/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devices;

import es.upv.dsic.gti_ia.core.ACLMessage;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.SingleAgent;
import house.Room;
/**
 *
 * @author admin
 */
public class HeaterAgent extends SingleAgent{
      
   
    private boolean active;
    
    public HeaterAgent(AgentID aid) throws Exception {
        super(aid);
       
    }
    
    public void init(){this.active = true;}
    
     public void onMessage(ACLMessage msg){
        //System.out.println("Hi! I'm agent "+this.getName()+" and I've received the message: "+msg.getContent());
        double temp = Double.parseDouble(msg.getContent().split(",")[1]);
        String season = msg.getContent().split(",")[0];
        
          if(temp>16){
                    System.out.println("Hi! I'm Heater agent and the current temperature is "+temp);
                }
                else{
                     System.out.println("Hi! I'm Heater agent, current temperature is "+temp+" and I turned on the heater");
            
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
