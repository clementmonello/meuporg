/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meuporg;

import java.io.*;
import java.lang.Thread;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrateur
 */
public class JV {

    /**
     * @param args the command line arguments
     */
    
    private static final ArrayList<Effet> listeEffet=new ArrayList<>();
    private static final ArrayList<Sort> listeSortGuerrier=new ArrayList<>();
    private static final ArrayList<Sort> listeSortPretre=new ArrayList<>();
    private static final ArrayList<Sort> listeSortMage=new ArrayList<>();
    private static final Equipe equipe1=new Equipe("1");
    private static final Equipe equipe2=new Equipe("2");
    
    public static void main(String[] args) throws Exception {
        
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//                      Elements Généraux
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        
        //création des effets
        
            Effet bruler=new EffetOffensif("bruler",2,0);
            Effet empoisonner=new EffetOffensif("empoisonner",4,0);
            Effet geler=new Effet("geler",1);
            Effet force=new Effet("force",2);
            Effet resistance=new Effet("resistance",2);
            Effet initiative=new Effet("initiative",2);

            listeEffet.add(bruler);
            listeEffet.add(empoisonner);
            listeEffet.add(geler);
            listeEffet.add(force);
            listeEffet.add(resistance);
            listeEffet.add(initiative);
        
        
        
        //création des sorts
        
            //sort mixtes
            //Sort s1=new Sort("skip tour",0,0,new ArrayList<>());
            
            //sort guerrier
        
            Sort sg1=new Sort("bloquer",1,new ArrayList<>(),new Cible("lanceur",new ArrayList<>()));
            sg1.getListeEffet().add(listeEffet.get(listeEffet.indexOf(resistance)));
            
            Sort sg2=new SortAttaque("coup d'epee",1,30,new ArrayList<>(),new Cible("personnage ennemis",new ArrayList<>()));
            
            //sort mage
            
            Sort sm1=new SortManaAttaque("boule de feu",1,20,20,new ArrayList<>(),new Cible("personnage ennemis",new ArrayList<>()));
            sm1.getListeEffet().add(listeEffet.get(listeEffet.indexOf(bruler)));
            
            Sort sm2=new SortManaAttaque("jet de poison",2,15,25,new ArrayList<>(),new Cible("equipe ennemie",new ArrayList<>()));
            sm2.getListeEffet().add(listeEffet.get(listeEffet.indexOf(empoisonner)));
            
            Sort sm3=new SortAttaque("Coup de Baton",1,10,new ArrayList<>(),new Cible("personnage ennemis",new ArrayList<>()));
            
            //sort pretre
            
            Sort sp1=new SortMana("protection divine",2,15,new ArrayList<>(),new Cible("personnage allie",new ArrayList<>()));
            sp1.getListeEffet().add(listeEffet.get(listeEffet.indexOf(resistance)));
            
            Sort sp2=new SortManaSoin("regeneration",2,15,30,new ArrayList<>(),new Cible("personnage allie",new ArrayList<>()));
            
            Sort sp3=new SortAttaque("Coup de bible",1,10,new ArrayList<>(),new Cible("personnage ennemis",new ArrayList<>()));
            
            
            
        //attribution des sorts aux classes
            
            //listeSortGuerrier.add(s1);
            listeSortGuerrier.add(sg1);
            listeSortGuerrier.add(sg2);
            
            //listeSortMage.add(s1);
            listeSortMage.add(sm1);
            listeSortMage.add(sm2);
            listeSortMage.add(sm3);
            
            //listeSortPretre.add(s1);
            listeSortPretre.add(sp1);
            listeSortPretre.add(sp2);
            listeSortPretre.add(sp3);
            
            
        
        //création des personnages
        
            Guerrier g1=new Guerrier("nicolas",130,150,100,10,new ArrayList<>(),listeSortGuerrier);
            Guerrier g2 =new Guerrier("clement",125,130,120,10,new ArrayList<>(),listeSortGuerrier);
            Pretre p1 =new Pretre("gregoire",70,100,120,70,120,new ArrayList<>(),listeSortPretre);
            Pretre p2 =new Pretre("thomas",70,110,120,60,125,new ArrayList<>(),listeSortPretre);
            Mage m1 =new Mage("Leo",110,100,110,60,120,new ArrayList<>(),listeSortMage);
            Mage m2 =new Mage("Arthur",115,100,120,50,130,new ArrayList<>(),listeSortMage);
        
        
        
        //création des équipes
        
            equipe1.addPersonnage(g1);
            equipe1.addPersonnage(m1);
            equipe1.addPersonnage(p1);

            equipe2.addPersonnage(g2);
            equipe2.addPersonnage(m2);
            equipe2.addPersonnage(p2);

        
        
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//                      Programme Principal
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


        Combat(equipe1,equipe2);
        
        //todo : 
        //Effet a fixer:
            //degats non basés sur la puissance du lanceur
        //dégats mal calculés
        //cooldown non utilisés
        //interface utilisateur, en quelque sorte, sur la sortie log
        //aléatoiriser la création de personnages, rajouter une rareté sur les personnages
        //enregistrer des informations sur un fichier texte
        //se renseigner sur Unity pour avoir une interface plus complete
        //.exe ?
        //completer des classes(rajouter du contenu)(optionnel)
        
    }
    
    
    
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//                      Méthodes Annexes
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    public static Guerrier guerrierUtilisateur(){
        System.out.println("nom ?");
        Scanner scNom=new Scanner(System.in);
        String nom=scNom.nextLine();

        System.out.println("attaque ?");
        Scanner scff=new Scanner(System.in);
        int ff=scff.nextInt();

        System.out.println("vie ?");
        Scanner scPv=new Scanner(System.in);
        int pv=scPv.nextInt();

        System.out.println("armure ?");
        Scanner scA=new Scanner(System.in);
        int a=scA.nextInt();

        System.out.println("vitesse d'ataque ?");
        Scanner scVa=new Scanner(System.in);
        int va=scVa.nextInt();

        Guerrier guerrier=new Guerrier(nom,ff,pv,a,va, new ArrayList<>(), listeSortGuerrier);
        return guerrier;
    }
    
    
    public static boolean Combat(Equipe e1,Equipe e2) throws Exception{
        
        int tour=0;
        ArrayList<Personnage> personnageEnCombat=new ArrayList<>();
        ArrayList<Personnage> personnageEnAttente;
        ArrayList<Personnage> personnageEnVie;
        Personnage attaquant;
        Personnage cible;
        ArrayList<Sort> sortUtilisable;
        Random rndInt=new Random();
        Sort sortUtilise;
        
        personnageEnCombat.addAll(e1.getListePersonnage());
        personnageEnCombat.addAll(e2.getListePersonnage());
        personnageEnVie=new ArrayList<>(personnageEnCombat);
        
        System.out.println("Combat opposant:");
        System.out.println(equipe1.toString());
        System.out.println(" à");
        System.out.println(equipe2.toString());
        System.out.println("");
        
        while ((equipe1.getVieTotale()>0)&&(equipe2.getVieTotale()>0)){
            
            //--------------Debut tour------------------------------------------
            
            tour++;
            personnageEnAttente=new ArrayList<>(personnageEnVie);
            
            System.out.println("--------------------------------------------------");
            System.out.println("");
            System.out.println("tour "+tour);
            
            for(int i=0;i<personnageEnVie.size();i++){
                
                boolean persoActuelVivant=true;
                
                for(int j=0;j<personnageEnVie.get(i).getListeEffet().size();j++){
                    
                    Effet effetTemp=personnageEnVie.get(i).getListeEffet().get(j);
                    
                    switch (effetTemp.getNom()){
                        case "bruler":
                            
                            String vieAvantDegat=Float.toString(personnageEnVie.get(i).getPointDeVie());
                            EffetOffensif effetTemp2=(EffetOffensif)effetTemp; 
                            personnageEnVie.get(i).degatRecu(effetTemp2.getDegat());
                            String vieApresDegat=Float.toString(personnageEnVie.get(i).getPointDeVie());
                            System.out.println("brulure de "+personnageEnVie.get(i).getNom()+": "+vieAvantDegat+"=>"+vieApresDegat+" "+personnageEnVie.get(i).getListeEffet().toString());
                            break;
                            
                        case "empoisonner":
                            
                            vieAvantDegat=Float.toString(personnageEnVie.get(i).getPointDeVie());
                            EffetOffensif effetTemp3=(EffetOffensif)effetTemp; 
                            personnageEnVie.get(i).degatRecu(effetTemp3.getDegat());
                            vieApresDegat=Float.toString(personnageEnVie.get(i).getPointDeVie());
                            System.out.println("poison de "+personnageEnVie.get(i).getNom()+": "+vieAvantDegat+"=>"+vieApresDegat+" "+personnageEnVie.get(i).getListeEffet().toString());
                            break;
                            
                        case "geler":
                            
                            personnageEnAttente.remove(personnageEnVie.get(i));
                            System.out.println(personnageEnVie.get(i).getNom()+" est gelé, il n'attaquera pas ce tour là "+personnageEnVie.get(i).getListeEffet().toString());
                            break;
                    }
                    
                    if(personnageEnVie.get(i).getPointDeVie()<=0){
                        System.out.println(personnageEnVie.get(i).getNom()+" est KO !");
                        
                        j=personnageEnVie.get(i).getListeEffet().size();
                        
                        personnageEnVie.get(i).getListeEffet().clear();
                        personnageEnAttente.remove(personnageEnVie.get(i));
                        personnageEnVie.remove(personnageEnVie.get(i));
                        
                        persoActuelVivant=false;
                    }
                }
                
                if(persoActuelVivant==false){
                    i--;
                }
            }
            
            for(int i=0;i<personnageEnVie.size();i++){
                for(int j=0;j<personnageEnVie.get(i).getListeEffet().size();j++){
                    
                    Effet effetTemp=personnageEnVie.get(i).getListeEffet().get(j);
                    effetTemp.setDuree(effetTemp.getDuree()-1);
                    
                    if(effetTemp.getDuree()==0){
                        personnageEnVie.get(i).getListeEffet().remove(effetTemp);
                        j--;
                    }
                }
            }
            
            System.out.println("");
            
            while((personnageEnAttente.size()>0)&&(equipe1.getVieTotale()>0)&&(equipe2.getVieTotale()>0)){
                
                //-------------Déroulement de l'attaque d'un personnage---------
                
                ArrayList<Personnage> listCibleAttaquable=new ArrayList<>();
                int iniMax=0;
                int indiceIniMax=0;
                for(int i=0;i<personnageEnAttente.size();i++){
                    double iniActuelle=personnageEnAttente.get(i).getInitiative()*Math.pow(1.2,Double.valueOf(personnageEnAttente.get(i).getNumberOfKindEffect("initiative")));
                    if(iniActuelle>iniMax){
                        indiceIniMax=i;
                        iniMax=personnageEnAttente.get(i).getInitiative();
                    }
                }
                
                attaquant=personnageEnAttente.get(indiceIniMax);
                
                personnageEnAttente.remove(attaquant);
                personnageEnVie.remove(attaquant);
                
                sortUtilisable=attaquant.creerSortUtilisable();
                sortUtilise=sortUtilisable.get(rndInt.nextInt(sortUtilisable.size()));
                
                try {
                    if(equipe1.hasPersonnage(attaquant)){
                        listCibleAttaquable=attaquant.creerCible(sortUtilise, equipe2);
                    }else{
                        listCibleAttaquable=attaquant.creerCible(sortUtilise, equipe1);
                    } 
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                
                if(listCibleAttaquable.size()==1){
                    
                    cible=listCibleAttaquable.get(0);
                    
                    String vieAvantAttaque=Float.toString(cible.getPointDeVie());
                    attaquant.attaquer(cible,sortUtilise);
                    String vieApresAttaque=Float.toString(cible.getPointDeVie());
                    
                    for(int i=0;i<sortUtilise.getListeEffet().size();i++){
                        if(sortUtilise.getListeEffet().get(i) instanceof EffetOffensif){
                            
                            EffetOffensif effetTemp=(EffetOffensif) sortUtilise.getListeEffet().get(i);
                            PersonnageAMana attaquantTemp=(PersonnageAMana) attaquant;
                            
                            if(sortUtilise instanceof SortManaAttaque){
                                SortManaAttaque sTemp= (SortManaAttaque)sortUtilise;
                                effetTemp=effetTemp.clone(sTemp.getPuissance()*(attaquantTemp.getPuissanceMana()/100));
                            }else{
                                SortAttaque sTemp2= (SortAttaque)sortUtilise;
                                effetTemp=effetTemp.clone(sTemp2.getPuissance()*(attaquantTemp.getPuissanceMana()/100));
                            }
                            
                            cible.getListeEffet().add(effetTemp);
                            
                        }else{
                            cible.getListeEffet().add(sortUtilise.getListeEffet().get(i).clone());
                        }   
                    }
                    
                    System.out.println(attaquant.getNom()+" utilise "+sortUtilise.getNom());
                    System.out.println("vie de "+cible.getNom()+": "+vieAvantAttaque+"=>"+vieApresAttaque+cible.getListeEffet().toString());

                    if(cible.getPointDeVie()==0){
                        personnageEnAttente.remove(cible);
                        personnageEnVie.remove(cible);
                        System.out.println(cible.getNom()+" est KO !");
                        cible.getListeEffet().clear();
                    }
                    
                }else if(sortUtilise.getCible().getTypeCible().equals("personnage allie")||sortUtilise.getCible().getTypeCible().equals("personnage ennemis")){
                    
                    cible=personnageEnVie.get(rndInt.nextInt(personnageEnVie.size()));
                    
                    String vieAvantAttaque=Float.toString(cible.getPointDeVie());
                    attaquant.attaquer(cible, sortUtilise);
                    String vieApresAttaque=Float.toString(cible.getPointDeVie());

                    for(int i=0;i<sortUtilise.getListeEffet().size();i++){
                        if(sortUtilise.getListeEffet().get(i) instanceof EffetOffensif){
                            
                            EffetOffensif effetTemp=(EffetOffensif) sortUtilise.getListeEffet().get(i);
                            
                            if(sortUtilise instanceof SortManaAttaque){
                                SortManaAttaque sTemp= (SortManaAttaque)sortUtilise;
                                effetTemp=effetTemp.clone(sTemp.getPuissance());
                            }else{
                                SortAttaque sTemp2= (SortAttaque)sortUtilise;
                                effetTemp=effetTemp.clone(sTemp2.getPuissance());
                            }
                            
                            cible.getListeEffet().add(effetTemp);
                            
                        }else{
                            cible.getListeEffet().add(sortUtilise.getListeEffet().get(i).clone());
                        }   
                    }
                    
                    System.out.println(attaquant.getNom()+" utilise "+sortUtilise.getNom());
                    System.out.println("vie de "+cible.getNom()+": "+vieAvantAttaque+"=>"+vieApresAttaque+cible.getListeEffet().toString());

                    if(cible.getPointDeVie()==0){
                        personnageEnAttente.remove(cible);
                        personnageEnVie.remove(cible);
                        System.out.println(cible.getNom()+" est KO !");
                        cible.getListeEffet().clear();
                    }
                    
                }else {  //(sortUtilise.getCible().getTypeCible().equals("autres allies")||sortUtilise.getCible().getTypeCible().equals("equipe ennemie"))
                    
                    System.out.println(attaquant.getNom()+" utilise "+sortUtilise.getNom());
                    
                    for(int i=0;i<listCibleAttaquable.size();i++){
                        
                        cible=listCibleAttaquable.get(i);
                        
                        String vieAvantAttaque=Float.toString(cible.getPointDeVie());
                        attaquant.attaquer(cible,sortUtilise);
                        String vieApresAttaque=Float.toString(cible.getPointDeVie());
                        
                        for(int j=0;j<sortUtilise.getListeEffet().size();j++){
                            if(sortUtilise.getListeEffet().get(j) instanceof EffetOffensif){

                                EffetOffensif effetTemp=(EffetOffensif) sortUtilise.getListeEffet().get(j);

                                if(sortUtilise instanceof SortManaAttaque){
                                    SortManaAttaque sTemp= (SortManaAttaque)sortUtilise;
                                    effetTemp=effetTemp.clone(sTemp.getPuissance());
                                }else{
                                    SortAttaque sTemp2= (SortAttaque)sortUtilise;
                                    effetTemp=effetTemp.clone(sTemp2.getPuissance());
                                }

                                cible.getListeEffet().add(effetTemp);

                            }else{
                                cible.getListeEffet().add(sortUtilise.getListeEffet().get(j).clone());
                            }   
                        }

                        System.out.println("vie de "+cible.getNom()+": "+vieAvantAttaque+"=>"+vieApresAttaque+cible.getListeEffet().toString());

                        if(cible.getPointDeVie()==0){
                            personnageEnAttente.remove(cible);
                            personnageEnVie.remove(cible);
                            System.out.println(cible.getNom()+" est KO !");
                            cible.getListeEffet().clear();
                        }
                    }
                }
                
                //--------Fin du déroulemment de l'attaque d'un personnage------
                
                personnageEnVie.add(attaquant);

                System.out.println("");
                
                try{
                    Thread.sleep(1000); 
                }catch(InterruptedException e){
                    System.out.println(e);
                }
            }
            
            //------------------------Fin d'un tour-----------------------------
            
            personnageEnAttente.clear();
            
            for(int i=0;i<personnageEnVie.size();i++){
                PersonnageAMana personnageActuel;
                if(personnageEnVie.get(i)instanceof PersonnageAMana){
                    personnageActuel=(PersonnageAMana) personnageEnVie.get(i);
                    personnageActuel.setPointMana(personnageActuel.getPointMana()+10);
                }
            }
            
            System.out.println("Fin du tour !");
            System.out.println("Les mages et prêtres ont regagnés 10 points de mana");
            System.out.println("");
            
        }
        
        //-----------------------Fin du combat----------------------------------
        
        if (equipe1.getVieTotale()>0){
            System.out.println("l'equipe "+equipe1.getNom()+" est vainqueure !");
        }else {
            System.out.println("l'equipe "+equipe2.getNom()+" est vainqueure !");
        }
        
    return true;    
    }
}