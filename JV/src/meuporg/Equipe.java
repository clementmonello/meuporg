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
public class Equipe {
    private String nom;
    private ArrayList<Personnage> listePersonnage;
    
    public Equipe(){
        this.nom="Default";
        this.listePersonnage=new ArrayList<>();
    }
    
    public Equipe(String n,ArrayList<Personnage> lp){
        this.nom=n;
        this.listePersonnage=lp;
    }
    
    public Equipe(String n){
        this.nom=n;
        this.listePersonnage=new ArrayList<>();
    }
    
    
    public String getNom(){
        return this.nom;
    }
    
    public ArrayList<Personnage> getListePersonnage(){
        return this.listePersonnage;
    }
    
    public void setNom(String n){
        this.nom=n;
    }
    
    public void setListePersonnage(ArrayList<Personnage> lp){
        this.listePersonnage=lp;
    }
    
    
    public void addPersonnage(Personnage p){
        this.getListePersonnage().add(p);
        p.setEquipe(this);
        
    }
    
    public void removePersonnage(Personnage p){
        this.getListePersonnage().remove(p);
        p.setEquipe(null);
    }
    
    public void clearPersonnage(){
        this.getListePersonnage().clear();
    }
    
    public boolean hasPersonnage(Personnage p)throws Exception{
        if(this.getListePersonnage().isEmpty()){
            throw new Exception("aucun personnage pour cette equipe");
        }else{
            for(int i=0;i<this.getListePersonnage().size();i++){
                if(this.getListePersonnage().get(i).equals(p)){
                    return true;
                }
            }
            return false;
        }
    }
    
    public float getVieTotale(){
        float tot=0;
        for(int i=0;i<this.getListePersonnage().size();i++){
            tot=tot+this.getListePersonnage().get(i).getPointDeVie();
        }
        return tot;
    }
    
    public String toStringParticipant(){
        String res="";
        for(int i=0;i<this.getListePersonnage().size();i++){
            res=res.concat("(");
            res=res.concat(getListePersonnage().get(i).getNom());
            res=res.concat(",");
            res=res.concat(getListePersonnage().get(i).getClass().descriptorString().replace("Lmeuporg/", ""));
            res=res.concat(")");
        }
        return res;
    }
    
    @Override
    public String toString(){
        return "nom:"+this.getNom()+" ("+toStringParticipant()+")";
    }
}
