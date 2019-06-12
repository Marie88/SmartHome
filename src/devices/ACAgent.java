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
public class ACAgent extends SingleAgent{

    private boolean active;
    File output;
    PrintWriter pw;
    
    public ACAgent(AgentID aid,File output,PrintWriter pw) throws Exception {
        super(aid);
        this.output = output;
        this.pw = pw;
       
    }
    public void init(){this.active = true;}
    
    public void onMessage(ACLMessage msg){
        
        
        double temperature = Double.parseDouble(msg.getContent().split(" ")[0]);
       
        if (msg.getContent().split(" ")[1].equals("turnON")) {
           
            Room.devices.get("AC").turnON();
            System.out.println("Hi! I'm AC agent and the current temperature is " + temperature + " and the AC is turned on");
            
        } else if(msg.getContent().split(" ")[1].equals("turnOFF")) {
           
            Room.devices.get("AC").turnOFF();
            System.out.println("Hi! I'm AC agent and the current temperature is " + temperature + " and the AC is turned off");
        }
        else if (msg.getContent().split(" ")[1].equals("ON")){
          
            System.out.println("Hi! I'm AC agent and the current temperature is " + temperature + " and the AC is on");
        }
        else if (msg.getContent().split(" ")[1].equals("OFF")){
          
             System.out.println("Hi! I'm AC agent and the current temperature is " + temperature + " and the AC is off");
        }
        // File csvOutputFile = new File("C:\\Users\\adminIIII\\Documents\\NetBeansProjects\\SmartHouse\\src\\resources\\Results.txt");
        try{
            pw.println(" "+temperature);
            
            double powerConsum = 0;
            for (String i : Room.devices.keySet()){
                if(Room.devices.get(i).isON()){
                     powerConsum += Room.devices.get(i).getConsumption();
                }
               
            }
            System.out.println(new DecimalFormat("##.##").format(powerConsum) + " kW power consumed in this h");
            pw.append(" "+new DecimalFormat("##.##").format(powerConsum));
            pw.flush();//assertTrue(csvOutputFile.exists());
        }catch(Exception e)
        {
            
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
