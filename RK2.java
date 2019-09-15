/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanderpolsolver;

import java.util.Arrays;

/**
 *
 * @author louis_3ofz47d
 */
public class RK2 extends AbstractCreator{
    //modified first derivative
    private double [] modiFZt;
    
    //X-vector
    private double [] X;
    
    //Actual Z-vector for the sequence
    public double [] Z;
    
    //first derivative
    private double [] FZtRK2;
    
    //constructor
    RK2(double [] input){
        
        //modified first derivative
        //these are values for the modified derivative
        modiFZt = new double[2*time.length+2];
        
        //the first derivative
        FZtRK2 = new double[2*time.length];
        
        FZtRK2[0] = FZt[0];
        FZtRK2[1] = FZt[1];
        
        //This is the X-vector
        X = new double[2];
        X[0] = 1;
        X[1] = 0;
        
        //initialising Z-vector
        Z = new double[2*time.length+2];
        Z[0] = constants[5];
        Z[1] = constants[6];       
        
        this.setMethod("Runge-Kutta 2");
    }
        
          
    @Override
    public String SolveEquation(){
        
        for(int i = 0; i<time.length; i++){
            X[0]=constants[3]*FZtRK2[2*i];
            X[1]=constants[3]*FZtRK2[2*i+1];
            
            if(i==0){
                System.out.println("These are values at n="+i);
                System.out.println("Initial vector x "+ Z[2*i]);
                System.out.println("Initial vector y "+ Z[2*i+1]);
                System.out.println("X vector: "+X[0]+X[1]);
                System.out.println("First derivative: "+FZtRK2[0]+FZtRK2[1]);
                System.out.println("");
            }
            else{
                FZtRK2[2*i]=Z[2*i+1];
                FZtRK2[2*i+1]=constants[0]*Math.sin(constants[2]*time[i])+
                       (constants[1]*(1-(Z[2*i]*Z[2*i])))*Z[2*i+1]-(Z[2*i]);
                }
            
                //set T=h
                constants[4]=constants[3];
                //MyReport page 3, paragraph RK2 algorithm, step 2c, last term
                modiFZt[2*i]=Z[2*i+1]+X[1];
                modiFZt[2*i+1]=constants[0]*Math.sin(constants[2]*(time[i]+constants[4]))+
                               (constants[1]*(1-((Z[2*i]+X[0])*(Z[2*i]+X[0])))*
                               (Z[2*i+1]+X[1])-(Z[2*i]+X[0]));
            
                //Just for training and double checking the values. Not necessary
                Z[2*i+2]=Z[2*i]+(constants[3]/2)*(FZtRK2[2*i]+modiFZt[2*i]);
                
                Z[2*i+3]=Z[2*i+1]+(constants[3]/2)*(FZtRK2[2*i+1]+modiFZt[2*i+1]);
            
                System.out.println("These are values at n="+i);
                System.out.println("Zx "+ Z[2*i+2]);
                System.out.println("Zy "+ Z[2*i+3]);
                System.out.println("modified derivative: "+modiFZt[0]+modiFZt[1]);
                System.out.println("");
                System.out.println(Arrays.toString(Z));
        }
        
        this.setMethod("Runge-Kutta 2");
        return Arrays.toString(Z);
    }
}
