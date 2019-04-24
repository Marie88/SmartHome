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
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author admin
 */
// need Tracker for season , weather , time of day
enum DeviceAgentIDs {

    ACAgent, LightAgent, HumidityAgent, BlindsAgent, HeaterAgent, WindowAgent;
}

public class LivingRoomAgent extends SingleAgent {

    Room livingroom;
    String season;
    
    private int iterator=0;
    boolean gotMsg = false;

    double currentT;
    double currentH;
    double currentL;
    
    double externalT;
    double externalH;
    double externalL;
    
    
     double t; double l; double h;
    public LivingRoomAgent(AgentID aid, Room room,String season) throws Exception {
        super(aid);
        this.livingroom = room;
        this.season = season;
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
        
        this.currentH = livingroom.modelHumidity(externalT, externalH);
        this.currentT = livingroom.modelTemp(externalT);
        this.currentL = livingroom.modelLuminosity(externalL);
        
        
        for (DeviceAgentIDs agentID : DeviceAgentIDs.values()) {

            ACLMessage msg = new ACLMessage();
            msg.setReceiver(new AgentID("" + agentID));
            msg.setSender(this.getAid());
            
            switch (agentID) {

                case LightAgent:
                {
                    livingroom.luminosity = currentL;
                    if(Room.devices.get("Lights").isON()){
                        
                        livingroom.luminosity +=Room.devices.get("Lights").getEnergy();
                         msg.setContent(""+livingroom.luminosity);
                    }
                    else{
                         msg.setContent(""+livingroom.luminosity);  
                    }
                    
                }
                    break;
                case ACAgent:{
                    double outTemp = this.currentT;//Double.parseDouble(getValuesFromSeedData(iterator)[7]);
                    double current = livingroom.modelTemp(outTemp);
                    msg.setContent(this.season+","+current);
                }
                    break;
                case HumidityAgent:{
                    double outHum = this.currentH;//Double.parseDouble(getValuesFromSeedData(iterator)[8]);
                    double outTemp = this.currentT;//Double.parseDouble(getValuesFromSeedData(iterator)[7]);
                    double current = livingroom.modelHumidity(outHum,outTemp);
                   
                    msg.setContent(this.season+"," + current);
                }
                    break;
                case BlindsAgent:{
                    double outLum = this.currentL;//Double.parseDouble(getValuesFromSeedData(iterator)[6]);
                    double current = livingroom.modelLuminosity(outLum);
                    msg.setContent(""+current); 
                }
                    break;
                case HeaterAgent:{
                    double outTemp = this.currentT;//Double.parseDouble(getValuesFromSeedData(iterator)[7]);
                    double current = livingroom.modelTemp(outTemp);
                    msg.setContent(this.season+","+current);
                }
                    break;
                case WindowAgent:{
                    double outHum = this.currentH;//Double.parseDouble(getValuesFromSeedData(iterator)[8]);
                    double outTemp = this.currentT;//Double.parseDouble(getValuesFromSeedData(iterator)[7]);
                    double currHum = livingroom.modelHumidity(outHum,outTemp);
                    double currTemp = livingroom.modelTemp(outTemp);
                    msg.setContent(this.season+"," + currTemp+","+currHum);
                }
                    break;
                default:
                    break;

            }

            this.send(msg);
        }
    }
}
