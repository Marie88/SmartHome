/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import es.upv.dsic.gti_ia.core.ACLMessage;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.SingleAgent;
import static java.lang.Thread.sleep;
import java.util.Random;

/**
 *
 * @author admin
 */

// need Tracker for season , weather , time of day
enum DeviceAgentIDs{
    ACAgent,LightAgent,HumidityAgent,BlindsAgent,HeaterAgent,WindowAgent;
}
public class LivingRoomAgent extends SingleAgent {
    
    private int temp;
    private int light;
    private int humidity;
    
    boolean gotMsg = false;
    
    int[] tempRange = {12,10,22,15,18,20,25,30}; //18-25 oC OK
    int[] lightRange ={20,30,40,50,60,10,20,15};// >40% OK
    int[] humRange =  {10,20,30,50,60,70,80,65};// <50% OK
    
    public LivingRoomAgent(AgentID aid,int temp,int light,int humidity) throws Exception {
        super(aid);
        this.temp = temp;
        this.light = light;
        this.humidity = humidity;
    }
    @Override
    public void onMessage(ACLMessage msg){
        System.out.println("Hi! I'm agent "+this.getName()+" and I've received the message: "+msg.getContent());
    }
    
    public static int getRandomFromArray(int[] array){
        int rnd =new Random().nextInt(array.length);
        return array[rnd];
    }
     public void execute(){
        System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");
      
         try {
                while(true){
                    sleep(5000);
                    broadcast();
                }
               
             } 
         catch (InterruptedException e) { this.gotMsg = false;   e.printStackTrace(); }
 
    }
     
     public  void broadcast(){
       for(DeviceAgentIDs agentID : DeviceAgentIDs.values()){
           
            ACLMessage msg = new ACLMessage();
            msg.setReceiver(new AgentID(""+agentID));
            msg.setSender(this.getAid());
            switch(agentID){
                
                case LightAgent:
                    msg.setContent(""+getRandomFromArray(lightRange));
                    break;
                case ACAgent:
                    msg.setContent(""+getRandomFromArray(tempRange));
                    break;
                case HumidityAgent:
                    msg.setContent(""+getRandomFromArray(humRange));
                    break;
                case BlindsAgent:
                     msg.setContent(""+getRandomFromArray(humRange));
                    break;
                case HeaterAgent:
                     msg.setContent(""+getRandomFromArray(tempRange));
                    break;
                case WindowAgent:
                    msg.setContent(""+getRandomFromArray(tempRange)+","+getRandomFromArray(humRange));
                    break;
                default:
                    break;
                
            }
            
           this.send(msg);
       }  
    }
}
