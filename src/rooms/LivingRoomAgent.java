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

   // int[] tempRange = {12, 10, 22, 15, 18, 20, 25, 30}; //18-25 oC OK
    int[] lightRange = {20, 30, 40, 50, 60, 10, 20, 15};// >40% OK
    //int[] humRange = {10, 20, 30, 50, 60, 70, 80, 65};// <50% OK

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
        for (DeviceAgentIDs agentID : DeviceAgentIDs.values()) {

            ACLMessage msg = new ACLMessage();
            msg.setReceiver(new AgentID("" + agentID));
            msg.setSender(this.getAid());
            switch (agentID) {

                case LightAgent:
                    msg.setContent("" + getRandomFromArray(lightRange)); //need incident solar radiation cvs
                    break;
                case ACAgent:{
                    double outTemp = Double.parseDouble(getValuesFromSeedData(iterator)[5]);
                    double current = livingroom.modelTemp(outTemp);
                    msg.setContent(""+current);
                }
                    break;
                case HumidityAgent:{
                    double outHum = Double.parseDouble(getValuesFromSeedData(iterator)[6]);
                    double outTemp = Double.parseDouble(getValuesFromSeedData(iterator)[5]);
                    double current = livingroom.modelHumidity(outHum,outTemp);
                   
                    msg.setContent("" + current);
                }
                    break;
                case BlindsAgent:
                    msg.setContent("" + Double.parseDouble(getValuesFromSeedData(iterator)[6]));
                    break;
                case HeaterAgent:{
                    double outTemp = Double.parseDouble(getValuesFromSeedData(iterator)[5]);
                    double current = livingroom.modelTemp(outTemp);
                    msg.setContent(""+current);
                }
                    break;
                case WindowAgent:{
                    double outHum = Double.parseDouble(getValuesFromSeedData(iterator)[6]);
                    double outTemp = Double.parseDouble(getValuesFromSeedData(iterator)[5]);
                    double current = livingroom.modelHumidity(outHum,outTemp);
                   
                    msg.setContent("" + current);
                }
                    break;
                default:
                    break;

            }

            this.send(msg);
        }
    }
}
