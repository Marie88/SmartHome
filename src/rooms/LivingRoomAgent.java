/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

import agent.Main;
import static agent.Main.season;
import es.upv.dsic.gti_ia.core.ACLMessage;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.SingleAgent;
import house.Room;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author admin
 */
// need Tracker for season , weather , time of day
enum DeviceAgentIDs {

    //ACAgent, LightAgent, HumidityAgent, BlindsAgent, HeaterAgent, WindowAgent;
    HeatAgent,HumidityAgent,LightAgent;
}

public class LivingRoomAgent extends SingleAgent {

   
   
    
    private int iterator=0;
    boolean gotMsg = false;
    PrintWriter pw;

    double externalT;
    double externalH;
    double externalL;
 
    public LivingRoomAgent(AgentID aid,PrintWriter pw) throws Exception {
        super(aid);
        this.pw=pw;
    }

    @Override
    public void onMessage(ACLMessage msg) {
        System.out.println("Hi! I'm agent " + this.getName() + " and I've received the message: " + msg.getContent());
     
    }

    public static int getRandomFromArray(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    
    public static String[] getValuesFromSeedData(int i) {
        return Main.fields.get(i);
    }

    public void execute() {
        System.out.println("Hi! I'm agent " + this.getName() + " and I start my execution");
        
        
        try {       
            while (true) {
                sleep(5000);
                if(iterator<167){
                    broadcast(iterator);
                    iterator++;
                }else{

                    pw.close();
                    iterator=0;
                }
            }

        } catch (InterruptedException e) {
            this.gotMsg = false;
            e.printStackTrace();
        }

    }

    public void broadcast(int iterator) {
    
        this.externalH=Double.parseDouble(getValuesFromSeedData(iterator)[8]);
        this.externalT=Double.parseDouble(getValuesFromSeedData(iterator)[7]);
        this.externalL=Double.parseDouble(getValuesFromSeedData(iterator)[6]);
        
       
        for (DeviceAgentIDs agentID : DeviceAgentIDs.values()) {

            ACLMessage msg = new ACLMessage();
            msg.setReceiver(new AgentID("" + agentID));
            msg.setSender(this.getAid());
            
            switch (agentID) {

                case LightAgent:
                {
                    msg.setContent(""+externalL);
                }
                    break;
           
               case HumidityAgent:
                {
                    msg.setContent("" + this.externalH+" "+this.externalT);
                }
                    break;
             
                case HeatAgent:
                {
                    msg.setContent(""+this.externalT);  
                }
                    break;
                default:
                    break;

            }
           
            this.send(msg);
        }
    }
}
