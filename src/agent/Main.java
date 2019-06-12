package agent;

import devices.ACAgent;
import devices.BlindsAgent;
import devices.GeneratorAgent;
import devices.HeaterAgent;
import devices.HumidifierAgent;
import goal_agents.HumidityAgent;
import devices.BulbAgent;
import devices.WindowAgent;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.AgentsConnection;
import goal_agents.HeatAgent;
import house.*;
import house.Room;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import rooms.BasementAgent;
import rooms.LivingRoomAgent;
import goal_agents.LightAgent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
        File csvOutputFile = new File("Results.txt");
        Path path=Paths.get("Results.txt");
        PrintWriter pw = new PrintWriter(new FileWriter(csvOutputFile,true));
        Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING);
        season = myObj.nextLine();
        myObj.close();

        getlines(seedData(season));
        
        AC ac=new AC(5.2,5); //subtracts 10oC per h
        Heater heat = new Heater(1.17,5);// adds 5oC per h
        Humidifier humid = new Humidifier(0.05,3); //adds/substracts 3% per h of humidity
        Lights lights = new Lights(0.16,200);// two incandescent bulbs
      
        Blinds blin = new Blinds();
        
        HashMap<String,Device> devices= new HashMap<String,Device>();
        
        devices.put("AC",ac);devices.put("Heater",heat);devices.put("Humidifier",humid);
        devices.put("Lights",lights);devices.put("Blinds",blin);
        
        Room room = new Room(12,100,4,devices);
        try {
            /**
             * Instantiating device agents
             */
            HumidifierAgent agent_hum = new HumidifierAgent(new AgentID("HumidifierAgent"),csvOutputFile,pw);
            LightAgent goal_agent_light = new LightAgent(new AgentID("LightAgent"),room);
            ACAgent agent_ac = new ACAgent(new AgentID("ACAgent"),csvOutputFile,pw);
            HumidityAgent goal_agent_hum = new HumidityAgent(new AgentID("HumidityAgent"),room);
            BlindsAgent agent_blin = new BlindsAgent(new AgentID("BlindsAgent"),csvOutputFile,pw);
            BulbAgent agent_bulb = new BulbAgent(new AgentID("BulbAgent"),csvOutputFile,pw);
            HeaterAgent agent_heat = new HeaterAgent(new AgentID("HeaterAgent"),csvOutputFile,pw);
            HeatAgent goal_agent_heat=new HeatAgent(new AgentID("HeatAgent"),room,season);
            //GeneratorAgent agent_gen = new GeneratorAgent(new AgentID("GeneratorAgent"),40);
           
            /**
             * Instantiating a room environment agent
             */
           
            LivingRoomAgent living_agent = new LivingRoomAgent(new AgentID("LivingRoomAgent"),pw);
            BasementAgent basement_agent = new BasementAgent(new AgentID("BasementAgent"), 12);
            /**
             *
             *
             *
             * Execute the agents
             */
            living_agent.start();

            goal_agent_light.start();
            //agent_ac.start();
            agent_hum.start();
            goal_agent_hum.start();
            agent_bulb.start();
            agent_blin.start();
           goal_agent_heat.start();
           agent_heat.start();
           

           // basement_agent.start();
           // agent_gen.start();
           //List<String> lala = agent_hum.humLines;
        } catch (Exception e) {
            logger.error("Error " + e.getMessage());
        }
    }

    public static BufferedReader seedData(String period) {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("resources/autumn.csv");
        if (period.startsWith("aut")) {
            is = classloader.getResourceAsStream("resources/autumn.csv");
            return new BufferedReader(new InputStreamReader(is));
        }

        if (period.startsWith("spr")) {
            is = classloader.getResourceAsStream("resources/spring.csv");
            return new BufferedReader(new InputStreamReader(is));
        }

        if (period.startsWith("sum")) {
            is = classloader.getResourceAsStream("resources/summer.csv");
            return new BufferedReader(new InputStreamReader(is));
        }

        if (period.startsWith("wint")) {
            is = classloader.getResourceAsStream("resources/winter.csv");
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
        //System.out.println(list[5] + " " + list[6]);
        return list;

    }
    
}
