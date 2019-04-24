
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package house;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author admin
 * 
 *  BUILDING MODEL  WITH SOURCES: 
 * 
 * 
 *       inner ambient temperature : T = (convHTC*wallArea(outTemp - wallTemp) ) / this.isolationIndex
 * 
 *              Twall = Tout + absorbCoef*incidentSolarRad/convHTC
 * 
        http://life.nthu.edu.tw/~labcjw/BioPhyChem/Spectroscopy/beerslaw.htm
        * 
        * http://help.vabi.nl/elements/en/index.html#!Documents/absorptioncoefficients.htm
        * 
                absorbCoef = 0.30 (white surface bricks )
        
        * 
        * 
        * 
        inner ambient humidity:     H = (vaporDensity/satVapourDensity)*100  ----->the saturation vapour density is determined by volume of the room and its a constant
                                                    always current vapour density <=saturation vapour density
        
                                                    saturation vapour density  = 6.11*10(7.5*T / 237.3*T)    T--air temperature
                                                    actual vapour density      = 6.11*10(7.5*Td / 237.3*Td)  Td-dew point
        
                                                     from https://www.wikihow.com/Calculate-Humidity
                                                     
                                                     * 
        inner luminosity :         direct normal irradiation  I = [ incidentSolarRad/ exp ( atmoCoef * sin(h)  ) ] * noOfWindows
        * https://www.engineeringtoolbox.com/light-level-rooms-d_708.html
        * 
        *    E = outLum / wallArea  Illuminance is flux over area of the room
        * 
        * 
        https://en.wikipedia.org/wiki/Air_mass_(solar_energy)
       
 */
public class Room {
    
    public double temperature;
    public double humidity;
    public double luminosity;
    
    public static HashMap<String,Device> devices;
    
    
    int area ; //15 m2
    double thermalConductivity; // 2.8 W/m*K
   // double wallThickness = 0.2; //m
    int wallTemp; // 
    int isolationIndex;
    int convHTC = 15; //   W/m2*K    //convective heat transfer coefficient
    
    int noOfWindows;
    double h = 22.5; //sun elevation
    int incidentSolarRad; //need in cvs //Measured in kWh/m2/day onto a horizontal surface:
    double atmoCoef = 1.5; // AM1.5 
    double absorbCoef=0.3;
    
    public Room(){}
    public Room(int area,int isolationIndex,int noOfWindows,HashMap<String,Device> devices){
        this.area = area;
        this.isolationIndex = isolationIndex;
        this.noOfWindows = noOfWindows;
        this.incidentSolarRad = 0;
        this.devices = devices;
    }
    
    public double modelWallTemp(double outTemp){  
        if(this.incidentSolarRad != 0){
            
            return  Math.round(outTemp +(this.incidentSolarRad*this.absorbCoef)/this.convHTC);
        }
        return 0;
    }
    public double modelTemp(double outTemp){
        double wall = modelWallTemp(outTemp);
        return  Math.round((convHTC*this.area*(outTemp - wallTemp) ) / this.isolationIndex);
        
    }

    
    public double modelHumidity(double humidity,double outTemp){ //https://en.wikipedia.org/wiki/Dew_point
        //Tdp = T - (100 - relativeHum / 5)
        double Tdp = Math.round(outTemp - ((100 - humidity) / 5));
        double vaporDensity = Math.round(6.11*10*(7.5*Tdp / 237.3*Tdp)); 
        double satVaporDensity = Math.round(6.11*10*(7.5*outTemp / 237.3*outTemp));
        return Math.round((vaporDensity/satVaporDensity)*100 );
         
    }
    
    
    public double modelLuminosity(double outLum){
       return Math.round(outLum/this.area);
    }

    public void setIncidentSolarRad(int incidentSolarRad) {
        this.incidentSolarRad = incidentSolarRad;
    }

    public HashMap<String, Device> getDevices() {
        return devices;
    }
    
}
