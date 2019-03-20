package agent;

import devices.ACAgent;
import devices.BlindsAgent;
import devices.GeneratorAgent;
import devices.HeaterAgent;
import devices.HumidityAgent;
import devices.LightAgent;
import devices.WindowAgent;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.AgentsConnection;
import rooms.BasementAgent;
import rooms.LivingRoomAgent;

public class Main {

public static void main(String[] args) {
/**
 * Setting the Logger
 */
        DOMConfigurator.configure("configuration/loggin.xml");
	Logger logger = Logger.getLogger(Main.class);

/**
 * Connecting to Qpid Broker
 */
	AgentsConnection.connect("localhost", 5672, "test", "guest","guest", false);



	try {
 /**
* Instantiating device agents
*/
                LightAgent agent_light = new LightAgent(new AgentID("LightAgent"),50);
                ACAgent    agent_ac = new ACAgent(new AgentID("ACAgent"),25);
                HumidityAgent agent_hum = new HumidityAgent(new AgentID("HumidityAgent"),30);
                BlindsAgent agent_blin = new BlindsAgent(new AgentID("BlindsAgent"),50);
                HeaterAgent agent_heat = new HeaterAgent(new AgentID("HeaterAgent"),25);
                GeneratorAgent agent_gen = new GeneratorAgent(new AgentID("GeneratorAgent"),12);
                WindowAgent agent_win = new WindowAgent(new AgentID("WindowAgent"),25,30);
/**
 * Instantiating a room environment agent
*/
		//Consumer consumerAgent = new Consumer(new AgentID("Consumer"));
                LivingRoomAgent living_agent = new LivingRoomAgent(new AgentID("LivingRoomAgent"),25,50,30);
                BasementAgent basement_agent = new BasementAgent(new AgentID("BasementAgent"),12);
/**



* Execute the agents
*/
		living_agent.start();
                
		agent_light.start();
                agent_ac.start();
                agent_hum.start();
                agent_blin.start();
                agent_heat.start();
                agent_win.start();
                
                basement_agent.start();
                agent_gen.start();
                

		} catch (Exception e) {logger.error("Error " + e.getMessage());}
 	}

}