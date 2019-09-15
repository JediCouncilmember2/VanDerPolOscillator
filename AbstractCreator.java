/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanderpolsolver;

/**
 *
 * @author louis_3ofz47d
 */
public abstract class AbstractCreator {
    
    private String method;
    protected static double [] constants;
    protected static double [] time;
    
    //The first derivative, vector represented as an array
    //First entry is for the x-coord., second for the y-coord.
    protected static double [] FZt;
    
    
    
    
    public String getMethod() {
        return method;
}
    protected void setMethod(String method) {
        this.method = method;
    }
    
    protected static double [] getConstants(){
        return constants;  
    }
    
    public static void setConstants(double [] constants) {
        AbstractCreator.constants = constants;
        //if negative constants are being entered throw exception
        for(int i=0; i<constants.length; i++){
            if(constants[i]<0){
                throw new IllegalArgumentException();
            }
        }
        
        FZt = new double[(int)(constants[4]/constants[3])+1];
        
        //creating array with amount of timeslots
        time = new double[(int)(constants[4]/constants[3])+1];
        
        //initial condition for 't'
        time[0]=0;
       
        //assigning values of 't' to array
        for(int i=0; i<time.length-1; i++){
            time[i+1]=time[i]+constants[3];
        }
        
        //this is the first derivative. x-coord. at 2i, y-coord. at 2i+1
        
            FZt[0]=constants[6];
            FZt[1]=constants[0]*Math.sin(constants[2]*time[0])+
                       (constants[1]*(1-(constants[5]*constants[5])))*
                    constants[6]-(constants[5]);
        
    }
    
    protected static double [] getTime() {
        return time;
    }

    
    //The following is the method to solve the Van Der Pol Equation
    public abstract String SolveEquation();
    
}
