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
public class LivingRoomAgent extends SingleAgent {
    
    private int temp;
    private int light;
    private int humidity;
    
    boolean gotMsg = false;
    int[] valuesArray={60,70,56,33,67};
    
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
        ACLMessage msg = new ACLMessage();
        ACLMessage send=new ACLMessage();
         try {
                send.setReceiver(new AgentID("LightAgent"));
                send.setSender(this.getAid());
                while(true){
                    sleep(3000);
                    send.setContent("Fiat Lux by "+getRandomFromArray(valuesArray)+" %");
                    this.send(send);
                }
               
             } 
         catch (InterruptedException e) { this.gotMsg = false;   e.printStackTrace(); }
         
    }
}
