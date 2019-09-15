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
public class RK4 extends AbstractCreator{
    /**Creating an array where the constants
     * A, mu, omega, h, T and the initial vector Z are stored
     */
    //For test purposes I will assume that the initial vector Z=(1 1}
    private double [] FZtRK4;
    private double [] Z;
    private double [] K1;
    private double [] K2;
    private double [] K3;
    private double [] K4;
    private double T;
    
    //inputs in the order A,mu,omega,h,T,Zx,Zy
    RK4(double [] input){

        FZtRK4 = new double[2*time.length];
        FZtRK4[0] = FZt[0];
        FZtRK4[1] = FZt[1];
        
        Z = new double[2*time.length+2];
        Z[0] = constants[5];
        Z[1] = constants[6];
        
        T = time[0] + constants[3]/2;
        
        K1 = new double[2];
        K1[0] = FZtRK4[0];
        K1[1] = FZtRK4[1];
        
        K2 = new double[2];
        K2[0] = Z[1] + (constants[3]*K1[1])/2;
        K2[1] = constants[0]*Math.sin(constants[2]*T)+
                (constants[1]*(1-(Z[0]+(constants[3]*K1[0])/2)*
                (Z[0]+(constants[3]*K1[0])/2)))*
                (Z[1]+(constants[3]*K1[1])/2)-
                (Z[0]+(constants[3]*K1[0])/2);
        
        K3 = new double[2];
        K3[0] = Z[1] + (constants[3]*K2[1])/2;
        K3[1] = constants[0]*Math.sin(constants[2]*T)+
                (constants[1]*(1-(Z[0]+(constants[3]*K2[0])/2)*
                (Z[0]+((constants[3]*K2[0])/2))))*
                (Z[1]+((constants[3]*K2[1])/2))-
                ((Z[0]+(constants[3]*K2[0])/2));
        
        K4 = new double[2];
        K4[0] = Z[1] + (constants[3]*K3[1]);
        K4[1] = constants[0]*Math.sin(constants[2]*(constants[3]+time[0]))+
                (constants[1]*(1-((Z[0]+(constants[3]*K3[0]))*
                (Z[0]+(constants[3]*K3[0])))))*
                (Z[1]+(constants[3]*K3[1]))-
                (Z[0]+constants[3]*K3[0]);
        
        this.setMethod("Runge-Kutta 4");
    }
    
    
    //Actual method that solves the equation, a little bit compplicated^^    
    @Override
    public String SolveEquation() {
        System.out.println("Hello. This is RK4 now.");
        for(int i = 0; i<time.length; i++){
            
        if(i==0){            
            //when t=0 there is no need to calculate so, 'empty' iteration
            System.out.println("Intitial vector x-coord. "+ Z[0]);
            System.out.println("Intitial vector y-coord. "+ Z[1]);
            System.out.println("This is the first derivative with initial"
                    + " conditions x-coord. "
                    + FZtRK4[0]);
            System.out.println("This is the first derivative with initial "
                    + "conditions y-coord."
                    + FZtRK4[1]);
            System.out.println("");
            
            //in following 5 lines I'm calculating already values for next iteration
            Z[2] = Z[0] + (constants[3]/6)*(K1[0]+2*K2[0]+2*K3[0]+K4[0]);
            Z[3] = Z[1] + (constants[3]/6)*(K1[1]+2*K2[1]+2*K3[1]+K4[1]);
            FZtRK4[2] = Z[3];
            FZtRK4[3] = constants[0]*Math.sin(constants[2]*time[1])+
                        (constants[1]*(1-(Z[2]*Z[2])))*Z[3]-(Z[2]);
            
            //These are the values after 1st iteration
            System.out.println("Here we go for Z1");
            System.out.println(Z[2]);
            System.out.println(Z[3]);
        } 
        else{  
            //no need to do this for t=0 because this been done in constructor
            T = time[i] + (constants[3]/2);
            
            //FZtRK4 is the derivative of Z; See notes at RK4 method 'F(Xn, tn)'
            FZtRK4[2*i] = Z[2*i+1];
            FZtRK4[2*i+1] = constants[0]*Math.sin(constants[2]*time[i])+
                            (constants[1]*(1-(Z[2*i]*Z[2*i])))*Z[2*i+1]-(Z[2*i]);            
            
            K1[0] = FZtRK4[2*i];
            K1[1] = FZtRK4[2*i+1];
            
            K2[0] = FZtRK4[2*i] + (constants[3]*K1[1])/2;
            K2[1] = constants[0]*Math.sin(constants[2]*T)+
                    (constants[1]*(1-(Z[2*i]+(constants[3]*K1[0])/2)*
                    (Z[2*i]+((constants[3]*K1[0])/2))))*
                    (Z[2*i+1]+((constants[3]*K1[1])/2))-
                    (Z[2*i]+((constants[3]*K1[0])/2));
            
            K3[0] = FZtRK4[2*i] + (constants[3]*K2[1])/2;
            K3[1] = constants[0]*Math.sin(constants[2]*T)+
                    (constants[1]*(1-(Z[2*i]+((constants[3]*K2[0]))/2)*
                    (Z[2*i]+((constants[3]*K2[0])/2))))*
                    (Z[2*i+1]+((constants[3]*K2[1])/2))-
                    (Z[2*i]+((constants[3]*K2[0])/2));
            
            K4[0] = FZtRK4[2*i] + (constants[3]*K3[1])/2;
            K4[1] = constants[0]*Math.sin(constants[2]*(constants[3]+time[i]))+
                    (constants[1]*(1-(Z[2*i]+(constants[3]*K3[0]))*
                    (Z[2*i]+(constants[3]*K3[0]))))*
                    (Z[2*i+1]+constants[3]*K3[1])-
                    (Z[2*i]+(constants[3]*K3[0]));
            
            //This is the Z vector split into x-coord. and y-coord.
            Z[2*i+2] = Z[2*i] + (constants[3]/6)*(K1[0]+2*K2[0]+2*K3[0]+K4[0]);
            Z[2*i+3] = Z[2*i+1] + (constants[3]/6)*(K1[1]+2*K2[1]+2*K3[1]+K4[1]);
            
            System.out.println("This is Zx at n="+i+" "+Z[2*i]);
            System.out.println("This is Zy at n="+i+" "+Z[2*i+1]);
            System.out.println(Arrays.toString(Z));
            System.out.println("");
        }
    }
    return Arrays.toString(Z);    
    }
    
}
