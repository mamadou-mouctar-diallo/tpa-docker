package uca.tpa.concess.entities;

public class ClientEntity {
    private int id;
    private int age;
    private String sexe;
    private double taux;
    private String situationFamiliale;
    private int nbEnfantsACharge;
    private boolean deuxiemeVoiture;
    private String immatriculation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public int getNbEnfantsACharge() {
        return nbEnfantsACharge;
    }

    public void setNbEnfantsACharge(int nbEnfantsACharge) {
        this.nbEnfantsACharge = nbEnfantsACharge;
    }

    public boolean isDeuxiemeVoiture() {
        return deuxiemeVoiture;
    }

    public void setDeuxiemeVoiture(boolean deuxiemeVoiture) {
        this.deuxiemeVoiture = deuxiemeVoiture;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }
}
