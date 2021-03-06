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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class BulbAgent extends SingleAgent{

    private boolean active;
    File output;
    PrintWriter pw;
    
    public BulbAgent(AgentID aid,File output,PrintWriter pw) throws Exception {
        super(aid);
        this.active = false;
        this.output = output;
        this.pw = pw;
    }
    
     public void onMessage(ACLMessage msg){
   
        double light = Double.parseDouble(msg.getContent().split(" ")[0]);
      
        if (msg.getContent().split(" ")[1].equals("turnON")) {
           
            Room.devices.get("Lights").turnON();
            System.out.println("Hi! I'm Bulb agent and the current luminosity is " + light + "lux and the bulbs are turned on");
            
        } else if(msg.getContent().split(" ")[1].equals("turnOFF")) {
           
            Room.devices.get("Lights").turnOFF();
            System.out.println("Hi! I'm Bulb agent and the current luminosity is " + light + "lux and the bulbs are turned off");
        }
        else if (msg.getContent().split(" ")[1].equals("ON")){
          
            System.out.println("Hi! I'm Bulb agent and the current luminosity is " + light + "lux and the bulbs are on");
        }
        else if (msg.getContent().split(" ")[1].equals("OFF")){
          
             System.out.println("Hi! I'm Bulb agent and the current luminosity is " + light + "lux and the bulbs are off");
        }
     // File csvOutputFile = new File("C:\\Users\\admin\\Documents\\NetBeansProjects\\SmartHouse\\src\\resources\\Results.txt");
      //  try (PrintWriter pw = new PrintWriter(output)) {
            pw.append(" "+light);
            pw.flush();
        //} catch (FileNotFoundException ex) {
        //    Logger.getLogger(ACAgent.class.getName()).log(Level.SEVERE, null, ex);
       // }
    }
    
    public void execute(){
	System.out.println("Hi! I'm agent "+this.getName()+" and I start my execution");

        ACLMessage msg2=new ACLMessage(ACLMessage.INFORM);
 	
        try {
            msg2=this.receiveACLMessage();
            this.send(msg2);
        } catch (InterruptedException ex) {
            
        }
        
        
    }
}
