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
public class Pretre extends PersonnageAMana{
    
    public Pretre(){
        super();
    }
    public Pretre(String n,int fp,int pv,int ini,int pm,int pum, ArrayList<Effet> le,ArrayList<Sort> ls){
        super(n,fp,pv,ini,pm,pum,le,ls);
    }
    
    @Override
    public void crier(){
        System.out.println("Amen !");
    }
    
    /*
    public boolean soigner(Personnage g){
        if (this.pointMana>=50){
            if (g.getPointDeVieMax()-g.getPointDeVie()>=this.soin){
                g.getPointDeVie()=g.getPointDeVie()+this.getSoin();
            } else {
            g.pointDeVie=g.pointDeVieMax;
            }
        }
        return true;
    }
    */
    
    @Override
    public String toString(){
        return "Pretre qui s'appele "+this.getNom();
    }
}