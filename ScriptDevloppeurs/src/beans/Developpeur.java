/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author pc
 */
public class Developpeur {
    private String nomdev;
    private String jour;
    private int nbrScript;

    public Developpeur(String nomdev, String jour, int nbrScript) {
        this.nomdev = nomdev;
        this.jour = jour;
        this.nbrScript = nbrScript;
    }

    public Developpeur() {
    }
    

    public String getNomdev() {
        return nomdev;
    }

    public String getJour() {
        return jour;
    }

    public int getNbrScript() {
        return nbrScript;
    }

    public void setNomdev(String nomdev) {
        this.nomdev = nomdev;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public void setNbrScript(int nbrScript) {
        this.nbrScript = nbrScript;
    }

    @Override
    public String toString() {
        return "Developpeur{" + "nomdev=" + nomdev + ", jour=" + jour + ", nbrScript=" + nbrScript + '}';
    }
    
    
    
    
    
}
