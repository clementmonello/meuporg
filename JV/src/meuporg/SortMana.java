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
public class SortMana extends Sort{
    protected int coutPM;
    
    public SortMana(){
        super();
        this.coutPM=1;
    }
    
    public SortMana(String n,int cd, int cpm,ArrayList<Effet> le, Cible c){
        super(n,cd,le,c);
        this.coutPM=cpm;
    }
    
    
    public void setCoutPM(int cpm){
        this.coutPM=cpm;
    }
    
    public int getCoutPM(){
        return this.coutPM;
    }
}