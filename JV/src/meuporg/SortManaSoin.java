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

public class SortManaSoin extends SortMana{
    
    private int soin;
    
    public SortManaSoin(){
        super();
        this.soin=10;
    }
    
    public SortManaSoin(String n,int cd,int so, int cpm,ArrayList<Effet> le, Cible c){
        super(n,cd,cpm,le,c);
        this.soin=so;
    }
    
    public int getSoin(){
        return this.soin;
    }
    
    public void setSoin(int so){
        this.soin=so;
    }
    
    public void SM_Soin(PersonnageAMana p1, Personnage p2){ //p1 soigne p2
        if(p2.getPointDeVie()+(this.getSoin()*(1+p1.getPuissanceMana()/100))<p2.getPointDeVieMax()){
            p2.setPointDeVie(p2.getPointDeVie()+this.getSoin()*(1+p1.getPuissanceMana()/100));
        }else{
            p2.setPointDeVie(p2.getPointDeVieMax());
        }
    }
}
