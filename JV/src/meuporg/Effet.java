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

/*
liste des effets : bruler, empoisonner, geler, force, resistance, initiative
effet implémentés : force, bruler, empoisonner, geler,resistance,initiative
*/

public class Effet {
    private String nom;
    private int duree;
    
    public Effet(){
        this.nom="default";
        this.duree=1;
    }
    
    public Effet(String n,int du){
        this.nom=n;
        this.duree=du;
    }
    
    
    public void setNom(String n){
        this.nom=n;
    }
    
    public void setDuree(int d){
        this.duree=d;
    }
    
    public String getNom(){
        return this.nom;
    }
    
    public int getDuree(){
        return this.duree;
    }
    
    @Override 
    public String toString(){
        return "nom:"+this.getNom()+" duree:"+this.getDuree();
    }
    
    @Override
    public Effet clone(){
        return new Effet(this.getNom(),this.getDuree());
    }
}
