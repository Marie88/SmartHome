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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import rooms.BasementAgent;
import rooms.LivingRoomAgent;

public class Main {

    public static String season;
    public static ArrayList<String[]> fields = new ArrayList<String[]>();

    public static void main(String[] args) throws IOException {
        /**
         * Setting the Logger
         */
        DOMConfigurator.configure("configuration/loggin.xml");
        Logger logger = Logger.getLogger(Main.class);

        /**
         * Connecting to Qpid Broker
         */
        AgentsConnection.connect("localhost", 5672, "test", "guest", "guest", false);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter the season you would like to simulate");

        season = myObj.nextLine();
        myObj.close();

        getlines(seedData(season));

        try {
            /**
             * Instantiating device agents
             */
            LightAgent agent_light = new LightAgent(new AgentID("LightAgent"), 50);
            ACAgent agent_ac = new ACAgent(new AgentID("ACAgent"), 25);
            HumidityAgent agent_hum = new HumidityAgent(new AgentID("HumidityAgent"), 30);
            BlindsAgent agent_blin = new BlindsAgent(new AgentID("BlindsAgent"), 50);
            HeaterAgent agent_heat = new HeaterAgent(new AgentID("HeaterAgent"), 25);
            GeneratorAgent agent_gen = new GeneratorAgent(new AgentID("GeneratorAgent"), 12);
            WindowAgent agent_win = new WindowAgent(new AgentID("WindowAgent"), 25, 30);
            /**
             * Instantiating a room environment agent
             */
            //Consumer consumerAgent = new Consumer(new AgentID("Consumer"));
            LivingRoomAgent living_agent = new LivingRoomAgent(new AgentID("LivingRoomAgent"), 25, 50, 30);
            BasementAgent basement_agent = new BasementAgent(new AgentID("BasementAgent"), 12);
            /**
             *
             *
             *
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

        } catch (Exception e) {
            logger.error("Error " + e.getMessage());
        }
    }

    public static BufferedReader seedData(String period) {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("resources/AutumnBaselWithHumidity.csv");
        if (period.startsWith("aut")) {
            is = classloader.getResourceAsStream("resources/AutumnBaselWithHumidity.csv");
            return new BufferedReader(new InputStreamReader(is));
        }

        if (period.startsWith("spr")) {
            is = classloader.getResourceAsStream("resources/SpringBaselWithHumidity.csv");
            return new BufferedReader(new InputStreamReader(is));
        }

        if (period.startsWith("sum")) {
            is = classloader.getResourceAsStream("resources/SummerBaselWithHumidity.csv");
            return new BufferedReader(new InputStreamReader(is));
        }

        if (period.startsWith("wint")) {
            is = classloader.getResourceAsStream("resources/WinterBaselWithHumidity.csv");
            return new BufferedReader(new InputStreamReader(is));
        }
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter the season you would like to simulate");

        season = myObj.nextLine();
        myObj.close();
        seedData(season);
        return new BufferedReader(new InputStreamReader(is));
    }

    public static void getlines(BufferedReader fin) throws IOException {
        String line;
        while ((line = fin.readLine()) != null) {
            fields.add(split(line));

        }
    }

    public static String[] split(String line) {
        String[] list;
        list = line.split(";");
        System.out.println(list[5] + " " + list[6]);
        return list;

    }
}
