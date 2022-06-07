/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meuporg;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Math;

/**
 *
 * @author Administrateur
 */
public class Personnage {
    private String nom;
    private float pointDeVie;
    private int forcePhysique; //pourcentage
    private float pointDeVieMax;
    private int initiative;
    private ArrayList<Effet> listeEffet;
    private ArrayList<Sort> listeSort;
    private Equipe equipe;
    
    public Personnage(){
        this.nom="gueu";
        this.forcePhysique=10;
        this.pointDeVie=100;
        this.pointDeVieMax=this.pointDeVie;
        this.initiative=100;
        this.listeEffet= new ArrayList<>();
        this.listeSort= new ArrayList<>();
    }
    
    public Personnage(String n,int fp,int pv,int ini, ArrayList<Effet> le,ArrayList<Sort> ls){
        this.nom=n;
        this.forcePhysique=fp;
        this.pointDeVie=pv;
        this.pointDeVieMax=this.pointDeVie;
        this.initiative=ini;
        this.listeEffet= le;
        this.listeSort= ls;
    }
    
    public String getNom(){
        return this.nom;
    }
    public int getForcePhysique(){
        return this.forcePhysique;
    }
    public float getPointDeVie(){
        return this.pointDeVie;
    }
    public float getPointDeVieMax(){
        return this.pointDeVieMax;
    }
    public int getInitiative(){
        return this.initiative;
    }
    public ArrayList<Effet> getListeEffet(){
        return this.listeEffet;
    }
    public ArrayList<Sort> getListeSort(){
        return this.listeSort;
    }
    public Equipe getEquipe(){
        return this.equipe;
    }
    
    public void setNom(String n){
        this.nom=n;
    }
    public void setPointDeVie(float pv){
        this.pointDeVie=pv;
    }
    public void setForcePhysique(int fp){
        this.forcePhysique=fp;
    }
    public void setPointDeVieMax(float pvm){
        this.pointDeVieMax=pvm;
    }
    public void setInitiative(int va){
        this.initiative=va;
    }
    public void setlisteEffet(ArrayList<Effet> le){
        this.listeEffet=le;
    }
    public void setlisteSort(ArrayList<Sort> ls){
        this.listeSort=ls;
    }
    public void setEquipe(Equipe e){
        this.equipe=e;
    }
    
    public void attaquer(Personnage g,Sort s) throws Exception{
        if(s instanceof SortAttaque){
            SortAttaque sTemp=(SortAttaque)s;
            g.degatRecu((float) (sTemp.getPuissance()*(this.getForcePhysique()/100)*Math.pow(1.2,Double.valueOf(this.getNumberOfKindEffect("force")))));
        }else{
            g.degatRecu((float) ((this.getForcePhysique()/100)*Math.pow(1.2,Double.valueOf(this.getNumberOfKindEffect("force")))));
        }
    }
    
    public ArrayList<Sort> creerSortUtilisable(){
        return this.getListeSort();
    }
    
    public ArrayList<Personnage> creerCible(Sort s,Equipe equipeAdverse)throws Exception{
        ArrayList<Personnage> listReturn=new ArrayList<>();
        String typeCible=s.getCible().getTypeCible();
        
        if(this.hasSort(s)==false){
            throw new Exception("Le personnage n'a pas ce sort");
        }else if(equipeAdverse.getListePersonnage().contains(this)){
            throw new Exception("Mauvaise équipe passée en parametre");
        }
        
        for(int i=0;i<equipeAdverse.getListePersonnage().size();i++){
            if(this.getEquipe().getListePersonnage().contains(equipeAdverse.getListePersonnage().get(i))){
                throw new Exception("Les équipes contiennent des personnages en communs");
            }
        }
        
        switch (typeCible) {
            case "lanceur":
                listReturn.add(this);
                return listReturn;
            case "personnage allie":
                for(int i=0;i<this.getEquipe().getListePersonnage().size();i++){
                    if(this.getEquipe().getListePersonnage().get(i)!=this){
                        listReturn.add(this.getEquipe().getListePersonnage().get(i));
                    }
                }
                return listReturn;
            case "autres allies":
                for(int i=0;i<this.getEquipe().getListePersonnage().size();i++){
                    if(this.getEquipe().getListePersonnage().get(i)!=this){
                        listReturn.add(this.getEquipe().getListePersonnage().get(i));
                    }
                }
                return listReturn;
            case "personnage ennemis":
                for(int i=0;i<equipeAdverse.getListePersonnage().size();i++){
                    listReturn.add(equipeAdverse.getListePersonnage().get(i)); 
                }
                return listReturn;
            case "equipe ennemie":
                for(int i=0;i<equipeAdverse.getListePersonnage().size();i++){
                    listReturn.add(equipeAdverse.getListePersonnage().get(i)); 
                }
                return listReturn;
            default:
                throw new Exception("Les équipes contiennent des personnages en communs");
        }
    }
    
    public void degatRecu(float dmg)throws Exception{
        if(dmg<0){
            throw new Exception("Dégats négatifs");
        }
        if (this.getPointDeVie()<dmg*(float) Math.pow(0.8,Double.valueOf(this.getNumberOfKindEffect("resistance")))){
            this.setPointDeVie(0);
        }else{
            this.setPointDeVie(this.getPointDeVie()-dmg*(float) Math.pow(0.8,Double.valueOf(this.getNumberOfKindEffect("resistance"))));
        }
    }
    
    
    public boolean hasSort(Sort s)throws Exception{
        if(this.getListeSort().isEmpty()){
            throw new Exception("aucun sort pour ce personage");
        }else{
            for(int i=0;i<this.getListeSort().size();i++){
                if(this.getListeSort().get(i).equals(s)){
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean isLanceur(Sort s) throws Exception{
        if(this.hasSort(s)==false){
            throw new Exception("Le personnage n'a pas ce sort");
        }else if(this.getListeSort().get(this.getListeSort().indexOf(s)).getCible().getListePersonnage().isEmpty()){
            throw new Exception("Aucune cible");
        }else if (s.getCible().getListePersonnage().size()>1){
            throw new Exception("Trop de cibles pour ce sort");
        }else if(this.getListeSort().get(this.getListeSort().indexOf(s)).getCible().getListePersonnage().size()==1){
            if ((this.getListeSort().get(this.getListeSort().indexOf(s)).getCible().getTypeCible().equals("lanceur"))&&(this.getListeSort().get(this.getListeSort().indexOf(s)).getCible().getListePersonnage().get(0).getNom().equals(this.getNom()))){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean isPersonnageAllie(Personnage p,Sort s) throws Exception{
        if(this.hasSort(s)==false){
            throw new Exception("Le personnage n'a pas ce sort");
        }else if(p.getEquipe().hasPersonnage(this)&&(p.getEquipe().hasPersonnage(p))==false){
            throw new Exception("Le lanceur et la cible ne font pas parti de l'equipe désignée");
        }else if(this.getListeSort().get(this.getListeSort().indexOf(s)).getCible().getListePersonnage().isEmpty()){
            throw new Exception("Aucune cible pour ce sort");
        }else if (s.getCible().getListePersonnage().size()>1){
            throw new Exception("Trop de cibles pour ce sort");
        }else if(this.getListeSort().get(this.getListeSort().indexOf(s)).getCible().getListePersonnage().size()==1){
            if ((this.getListeSort().get(this.getListeSort().indexOf(s)).getCible().getTypeCible().equals("personnage allié"))&&(this.getListeSort().get(this.getListeSort().indexOf(s)).getCible().getListePersonnage().get(0).getNom().equals(this.getEquipe().getListePersonnage().get(this.getEquipe().getListePersonnage().indexOf(p)).getNom()))){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean isAutresAllie(Sort s)throws Exception{
        if(this.hasSort(s)==false){
            throw new Exception("Le personnage n'a pas ce sort");
        }else if(this.getEquipe().hasPersonnage(this)==false){
            throw new Exception("Le lanceur ne fait pas parti de l'equipe désignée");
        }else if(s.getCible().getTypeCible().equals("autres allies")){
            ArrayList<Personnage> listTemp=new ArrayList<>();
            for(int i=0;i<this.getEquipe().getListePersonnage().size();i++){
                if(this.getEquipe().getListePersonnage().get(i)!=this){
                    listTemp.add(this.getEquipe().getListePersonnage().get(i));
                }
            }
            if(listTemp.containsAll(s.getCible().getListePersonnage())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean isPersonnageEnnemis(Personnage p,Sort s) throws Exception{
        if(this.hasSort(s)==false){
            throw new Exception("Le personnage n'a pas ce sort");
        }else if(this.getEquipe().hasPersonnage(this)==false){
            throw new Exception("Le lanceur ne fait pas parti de l'equipe désignée");
        }else if(s.getCible().getTypeCible().equals("personnage ennemis")){
            for(int i=0;i<this.getEquipe().getListePersonnage().size();i++){
                if(this.getEquipe().getListePersonnage().get(i)==p){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public boolean isEquipeEnnemis(Sort s,Equipe e) throws Exception{
        if(this.hasSort(s)==false){
            throw new Exception("Le personnage n'a pas ce sort");
        }else if(e==this.getEquipe()){
            throw new Exception("Ciblage de sa propre équipe");
        }else if(s.getCible().getTypeCible().equals("equipe ennemie")){
            if(e.getListePersonnage().containsAll(s.getCible().getListePersonnage())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    public void effetReduit(){
        for(int i=0;i<this.getListeEffet().size();i++){
            this.getListeEffet().get(i).setDuree(this.getListeEffet().get(i).getDuree()-1);
        }
    }
    
    public int getNumberOfKindEffect(String n){
        int nbTemp=0;
        for(int i=0;i<this.getListeEffet().size();i++){
            if(this.getListeEffet().get(i).getNom().equals(n)){
                nbTemp++;
            }
        }
        return nbTemp;
    }
}