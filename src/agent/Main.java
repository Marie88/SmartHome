package agent;

import devices.ACAgent;
import devices.BlindsAgent;
import devices.HeaterAgent;
import devices.HumidityAgent;
import devices.LightAgent;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.AgentsConnection;
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
                
/**
 * Instantiating a room environment agent
*/
		//Consumer consumerAgent = new Consumer(new AgentID("Consumer"));
                LivingRoomAgent enviroAgent = new LivingRoomAgent(new AgentID("LivingRoomAgent"),25,50,30);

/**

14 2.2. Developing and executing a first agent

* Execute the agents
*/
		enviroAgent.start();
                
		agent_light.start();
                agent_ac.start();
                agent_hum.start();
                agent_blin.start();
                agent_heat.start();
                

		} catch (Exception e) {logger.error("Error " + e.getMessage());}
 	}

}