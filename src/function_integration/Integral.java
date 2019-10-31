/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function_integration;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 * @author User
 */
public class Integral {
    private double α;
    private double β;
    private double μ;
    private double ε;
    private double a;
    private double b;
    private int param;
    private int n;
    private int m;
      
    public Integral(double α, double β, double μ, double ε, double a, double b, int param, int n, int m) {
        this.α = α;
        this.β = β;
        this.μ = μ;
        this.ε = ε;
        this.a=a;
        this.b=b;
        this.param=param;
        this.n=n;
        this.m=m;
    }
    
    public double getFunction(double α, double β, double μ, double ε, double x) {
        return α*sin(β*x)*cos(ε/((x-μ)*(x-μ)));
        //return sin(x);
    }
    
    public double[] getIntegral (double x, double k) {
        double xi[] = new double[n];
        switch (param) {
            case (1):
            for (int i=0;i<m;i++) {
                x=getFunction(k, β, μ, ε, x);
            }
        
            for (int i=0;i<n;i++) {
                x=getFunction(k, β, μ, ε, x);
                xi[i] = x;
            }
            break;
            case (2):
            for (int i=0;i<m;i++) {
                x=getFunction(α, k, μ, ε, x);
            }
        
            for (int i=0;i<n;i++) {
                x=getFunction(α, k, μ, ε, x);
                xi[i] = x;
            }
            break;
            case (3):
                for (int i=0;i<m;i++) {
                x=getFunction(α, β, k, ε, x);
            }
        
            for (int i=0;i<n;i++) {
                x=getFunction(α, β, k, ε, x);
                xi[i] = x;
            }
            break;
            case (4):
                for (int i=0;i<m;i++) {
                x=getFunction(α, β, μ, k, x);
            }
        
            for (int i=0;i<n;i++) {
                x=getFunction(α, β, μ, k, x);
                xi[i] = x;
            }
            break;
        }
        
        return xi;
    }
}