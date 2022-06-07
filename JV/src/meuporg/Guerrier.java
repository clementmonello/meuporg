package meuporg;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrateur
 */
public class Guerrier extends Personnage{
    private int armure;
    
    public Guerrier(){
        super();
        this.armure=20;
    }
    public Guerrier(String n, int fp, int pv,int ini, int a, ArrayList<Effet> le,ArrayList<Sort> ls){
        super(n,fp,pv,ini,le,ls);
        this.armure=a;
    }
    
    public int getArmure(){
        return this.armure;
    }
    
    public void setNom(int a){
        this.armure=a;
    }
    
    public void crier(){
        System.out.println("AOUH ! AOUH ! AOUH !");
    }
    
    
    @Override
    public void degatRecu(float dmg){
        if(dmg<this.getArmure()){
            dmg=0;
        }else{
            dmg=dmg-this.getArmure();
        }
        if (this.getPointDeVie()<dmg){
            this.setPointDeVie(0);
        }else{
            this.setPointDeVie(this.getPointDeVie()-dmg);
        }
    }
    
    @Override
    public String toString(){
        return "Guerrier qui s'appele "+this.getNom();
    }
}


