package metier;

import java.util.ArrayList;


/**
 * Created by ssonn on 20/10/2017.
 */

public class Enchainement {
    String posture;
    int nbResp;
    public static ArrayList<Enchainement> tousLesEnchainements;
    public Enchainement(String unePosture, int unNbResp){
        this.posture = unePosture;
        this.nbResp = unNbResp;
        this.tousLesEnchainements = new ArrayList<>();
    }
    public String getPosture(){
        return posture;
    }
    public int getNbResp(){
        return nbResp;
    }
    public static void setTousLesEnchainements(ArrayList<Enchainement> listEnchainement){
        tousLesEnchainements = listEnchainement;
    }

    public static void addEnchainement(Enchainement unEnchainement){
        tousLesEnchainements.add(unEnchainement);
    }
}
