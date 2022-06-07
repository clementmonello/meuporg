/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meuporg;

/**
 *
 * @author Propriétaire
 */
public class EffetOffensif extends Effet{
    private int degat;
    
    public EffetOffensif(){
        super();
        this.degat=0;
    }
    
    public EffetOffensif(String n, int du, int de){
        super(n,du);
        this.degat=de;
    }
    
    public int getDegat(){
        return this.degat;
    }
    
    public void setDegat(int de){
        this.degat=de;
    }
    
    public EffetOffensif clone(int de){
        return new EffetOffensif(this.getNom(),this.getDuree(),de);
    }
    
    @Override 
    public String toString(){
        return "nom:"+this.getNom()+" duree:"+this.getDuree()+" degat:"+this.getDegat();
    }
}
