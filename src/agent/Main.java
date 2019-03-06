package agent;

import devices.ACAgent;
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
		//Sender senderAgent = new Sender(new AgentID("Sender"));
                LightAgent agent1 = new LightAgent(new AgentID("LightAgent"),50);
                ACAgent    agent2 = new ACAgent(new AgentID("ACAgent"),25);
/**
 * Instantiating a room environment agent
*/
		//Consumer consumerAgent = new Consumer(new AgentID("Consumer"));
                LivingRoomAgent enviroAgent = new LivingRoomAgent(new AgentID("LivingRoomAgent"),25,50);

/**

14 2.2. Developing and executing a first agent

* Execute the agents
*/
		enviroAgent.start();
		agent1.start();
               // agent2.start();

		} catch (Exception e) {logger.error("Error " + e.getMessage());}
 	}

}