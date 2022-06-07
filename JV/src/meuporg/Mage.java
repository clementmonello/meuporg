/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meuporg;

import java.util.ArrayList;

/**
 *
 * @author Administrateur
 */
public class Mage extends PersonnageAMana{
    
    public Mage(){
        super();
    }
    public Mage(String n,int fp,int pv,int ini, int pm,int pum, ArrayList<Effet> le,ArrayList<Sort> ls){
        super(n,fp,pv,ini,pm,pum,le,ls);
    }
    
    
    @Override
    public void crier(){
        System.out.println("ABRACADABRA !");
    }
    
    @Override
    public String toString(){
        return "Mage qui s'appele "+this.getNom();
    }
}
