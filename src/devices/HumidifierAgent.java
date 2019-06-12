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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author admin
 */
public class HumidifierAgent extends SingleAgent{
  
    private boolean active;
    //public List<String> humLines ;
    File output;
   PrintWriter pw;
    
    public HumidifierAgent(AgentID aid,File output,PrintWriter pw) throws Exception {
        super(aid);
        this.output = output;
        this.pw = pw;
       
    }
    
    public void init(){this.active = true; }//humLines= new ArrayList<>();}
    
      public void onMessage(ACLMessage msg){
          
       double humidity = Double.parseDouble(msg.getContent().split(" ")[0]);
       
        
        if (msg.getContent().split(" ")[1].equals("turnON")) {
           
            Room.devices.get("Humidifier").turnON();
            System.out.println("Hi! I'm Humidifier agent and the current humidity is " + humidity + "% and the humidifier is turned on");
            
        } else if(msg.getContent().split(" ")[1].equals("turnOFF")) {
           
            Room.devices.get("Humidifier").turnOFF();
            System.out.println("Hi! I'm Humidifier agent and the current humidity is " + humidity + "% and the humidifier is turned off");
        }
        else if (msg.getContent().split(" ")[1].equals("ON")){
          
            System.out.println("Hi! I'm Humidifier agent and the current humidity is " + humidity + "% and the humidifier is on");
        }
        else if (msg.getContent().split(" ")[1].equals("OFF")){
          
             System.out.println("Hi! I'm Humidifier agent and the current humidity is " + humidity + "% and the humidifier is off");
        }
        //humLines.add(""+humidity);
    // File csvOutputFile = new File("C:\\Users\\admin\\Documents\\NetBeansProjects\\SmartHouse\\src\\resources\\Results.txt");
       // try (PrintWriter pw = new PrintWriter(output)) {
            pw.append(" "+humidity);
            
       // } catch (FileNotFoundException ex) {
         //   Logger.getLogger(ACAgent.class.getName()).log(Level.SEVERE, null, ex);
        //}
       
        pw.flush();
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
