/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meuporg;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrateur
 */
public class PersonnageAMana extends Personnage{
    private int pointMana;
    private int puissanceMana;
    
    public PersonnageAMana(){
        super();
        this.pointMana=100;
        this.puissanceMana=100; //pourcentage
    }
    public PersonnageAMana(String n,int fp,int pv,int va, int pm,int pum, ArrayList<Effet> le,ArrayList<Sort> ls){
        super(n,fp,pv,va,le,ls);
        this.pointMana=pm;
        this.puissanceMana=pum;
    }
    
    public int getPointMana(){
        return this.pointMana;
    }
    public int getPuissanceMana(){
        return this.puissanceMana;
    }
    
    public void setPointMana(int n){
        this.pointMana=n;
    }
    public void setPuissanceMana(int n){
        this.puissanceMana=n;
    }
    
    public void crier(){
        System.out.println("cris du personnage a mana !");
    }
    
    
    public void attaquer(Personnage p,Sort s) throws Exception{
        switch (s.getClass().getTypeName()){
            case "meuporg.SortManaSoin":
                SortManaSoin sTemp=(SortManaSoin) s;
                sTemp.SM_Soin(this, p);
                this.setPointMana(this.getPointMana()-sTemp.getCoutPM());
                break;
            case "meuporg.SortManaAttaque":
                SortManaAttaque sTemp2=(SortManaAttaque) s;
                p.degatRecu((float) (sTemp2.getPuissance()*(this.getPuissanceMana()/100)*Math.pow(1.2,Double.valueOf(this.getNumberOfKindEffect("force")))));
                this.setPointMana(this.getPointMana()-sTemp2.getCoutPM());
                break;
            case "meuporg.SortAttaque":
                SortAttaque sTemp3=(SortAttaque) s;
                p.degatRecu((float) (sTemp3.getPuissance()*(this.getForcePhysique()/100)*Math.pow(1.2,Double.valueOf(this.getNumberOfKindEffect("force")))));
                break;
        }
    }
    
    @Override
    public ArrayList<Sort> creerSortUtilisable(){
        ArrayList<Sort> sortUtilisable=new ArrayList<>();
        SortMana sortActuel;
        for(int i=0;i<this.getListeSort().size();i++){
            if(this.getListeSort().get(i) instanceof SortMana){
                sortActuel=(SortMana) this.getListeSort().get(i);
                if (sortActuel.getCoutPM()<this.getPointMana()){
                    sortUtilisable.add(sortActuel);
                }
            }
        }
        if(sortUtilisable.isEmpty()){
            for(int i=0;i<this.getListeSort().size();i++){
                if(!(this.getListeSort().get(i) instanceof SortMana)){
                    sortUtilisable.add(this.getListeSort().get(i));
                }
            } 
        }
        return sortUtilisable;
    }
    
    @Override
    public String toString(){
        return "Personnage qui s'appele"+this.getNom();
    }
}
