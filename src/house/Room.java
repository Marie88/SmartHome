/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package house;

/**
 *
 * @author admin
 * 
 * 
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
                
        inner ambient humidity:     H = (vaporDensity/satVapourDensity)*100  ----->the saturation vapour density is determined by volume of the room and its a constant
                                                    always current vapour density <=saturation vapour density
        
                                                    saturation vapour density  = 6.11*10(7.5*T / 237.3*T)    T--air temperature
                                                    actual vapour density      = 6.11*10(7.5*Td / 237.3*Td)  Td-dew point
        
                                                    
        inner luminosity :          I = [ incidentSolarRad/ exp ( atmoCoef * sin(h)  ) ] * noOfWindows
        https://en.wikipedia.org/wiki/Air_mass_(solar_energy)
        from https://www.wikihow.com/Calculate-Humidity
 */
public class Room {
    
    double temperature;
    double humidity;
    double luminosity;
    
    int wallArea; //12 m2
    double thermalConductivity; // 2.8 W/m*K
   // double wallThickness = 0.2; //m
    int wallTemp; // 
    int isolationIndex;
    int convHTC = 15; //   W/m2*K    //convective heat transfer coefficient
    
    int noOfWindows;
    double h = 1.5707; //sun elevation
    int incidentSolarRad; //need in cvs
    double atmoCoef = 1.5; // AM1.5 
    double absorbCoef=0.3;
    
    public Room(){}
    public Room(int wallArea,int isolationIndex,int noOfWindows){
        this.wallArea = wallArea;
        this.isolationIndex = isolationIndex;
        this.noOfWindows = noOfWindows;
       
        this.incidentSolarRad = 0;
    }
    
    public double modelWallTemp(double outTemp){  
        if(this.incidentSolarRad != 0){
            
            return  Math.round(outTemp +(this.incidentSolarRad*this.absorbCoef)/this.convHTC);
        }
        return 0;
    }
    public double modelTemp(double outTemp){
        double wall = modelWallTemp(outTemp);
        this.temperature = Math.round((convHTC*this.wallArea*(outTemp - wallTemp) ) / this.isolationIndex);
        return temperature;
    }

    
    public double modelHumidity(double humidity,double outTemp){ //https://en.wikipedia.org/wiki/Dew_point
        //Tdp = T - (100 - relativeHum / 5)
        double Tdp = Math.round(outTemp - ((100 - humidity) / 5));
        double vaporDensity = Math.round(6.11*10*(7.5*Tdp / 237.3*Tdp)); 
        double satVaporDensity = Math.round(6.11*10*(7.5*outTemp / 237.3*outTemp));
        this.humidity = Math.round((vaporDensity/satVaporDensity)*100 );
        return humidity;
    }
    
    
    public double modelLuminosity(double incidentSolarRad){
       // I = [ incidentSolarRad/ exp ( atmoCoef * sin(h)  ) ] * noOfWindows
       this.luminosity = Math.round(incidentSolarRad/Math.exp(this.atmoCoef*Math.sin(h)));
       return luminosity;
    }
    
  
    
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setLuminosity(double luminosity) {
        this.luminosity = luminosity;
    }

    public void setIncidentSolarRad(int incidentSolarRad) {
        this.incidentSolarRad = incidentSolarRad;
    }
    
}
