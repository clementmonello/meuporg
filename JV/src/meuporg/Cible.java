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
 * 
 * les différentes cibles sont: lanceur(Personnage), personnage allié(Personnage), autres alliés(Equipe), personnage ennemis(Personnage), équipe ennemie(Equipe), tous(ArrayList Personnage)
 */
public class Cible {
    private String typeCible;
    private ArrayList<Personnage> listePersonnage;

    public Cible(){
        this.typeCible="lanceur";
        this.listePersonnage=new ArrayList<>();
    }

    public Cible(String tc,ArrayList<Personnage> lp){
        this.typeCible=tc;
        this.listePersonnage=lp;
    }

    public String getTypeCible(){
        return this.typeCible;
    }

    public ArrayList<Personnage> getListePersonnage(){
        return this.listePersonnage;
    }
    
    public void setTypeCible(String tc){
        this.typeCible=tc;
    }
    
    public void setListePersonnage(String lp){
        this.typeCible=lp;
    }
}
