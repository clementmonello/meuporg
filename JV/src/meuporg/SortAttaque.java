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
public class SortAttaque extends Sort{
    private int puissance; //dégat pour 100% d'éfficacité(force physique ou puissance mana) par le personnage qui l'utilise 
    
    public SortAttaque(){
        super();
        this.puissance=0;
    }
    
    public SortAttaque(String n,int cd,int p,ArrayList<Effet> le,Cible c){
        super(n,cd,le,c);
        this.puissance=p;
    }
    
    public void setPuissance(int p){
        this.puissance=p;
    }
    
    public int getPuissance(){
        return this.puissance;
    }
    
    public void S_Attaque(Personnage p1, Personnage p2) throws Exception{
        p2.degatRecu((p1.getForcePhysique()/100)*this.getPuissance());
    }
}
