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
public class SortManaAttaque extends SortMana {
    private int puissance;
    
    public SortManaAttaque(){
        super();
        this.puissance=10;
    }
    
    public SortManaAttaque(String n,int cd,int p, int cpm,ArrayList<Effet> le,Cible c){
        super(n,cd,cpm,le,c);
        this.puissance=p;
    }
    
    
    public void setPuissance(int p){
        this.puissance=p;
    }
    
    public int getPuissance(){
        return this.puissance;
    }
    
    
    public void SM_Attaque(PersonnageAMana p1, Personnage p2) throws Exception{
        p2.degatRecu((p1.getPuissanceMana()/100)*this.getPuissance());
    }
}
