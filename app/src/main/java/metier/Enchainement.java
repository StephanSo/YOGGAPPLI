package metier;

/**
 * Created by ssonn on 20/10/2017.
 */

public class Enchainement {
    String posture;
    int nbResp;
    public Enchainement(String unePosture, int unNbResp){
        this.posture = unePosture;
        this.nbResp = unNbResp;
    }
    public String getPosture(){
        return posture;
    }
    public int getNbResp(){
        return nbResp;
    }
}
