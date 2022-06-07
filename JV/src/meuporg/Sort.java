/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meuporg;
import java.util.ArrayList;

/**
 *
 * @author Propriétaire
 */
public class Sort {
    private String nom;
    private int cooldown;
    private ArrayList<Effet> listeEffet;
    private Cible cible; //les différentes cibles sont: lanceur(Personnage), personnage allié(Personnage), autres alliés(Equipe), personnage ennemis(Personnage), équipe ennemie(Equipe), tous(ArrayList<Personnage>)
    
    public Sort(){
        this.nom="default";
        this.cooldown=1;
        this.listeEffet= new ArrayList<>();
        this.cible=new Cible();
    }
    
    public Sort(String n,int cd,ArrayList<Effet> le,Cible c){
        this.nom=n;
        this.cooldown=cd;
        this.listeEffet= le;
        this.cible=c;
    }
    
    
    public void setNom(String n){
        this.nom=n;
    }
    
    public void setCooldown(int cd){
        this.cooldown=cd;
    }
    
    
    public void setListeEffet(ArrayList<Effet> le){
        this.listeEffet=le;
    }
    
    public void setCible(Cible lc){
        this.cible=lc;
    }
    
    
    public int getCooldown(){
        return this.cooldown;
    }
    
    public String getNom(){
        return this.nom;
    }
    
    public ArrayList<Effet> getListeEffet(){
        return this.listeEffet;
    }
    
    public Cible getCible(){
        return this.cible;
    }
    
} 

