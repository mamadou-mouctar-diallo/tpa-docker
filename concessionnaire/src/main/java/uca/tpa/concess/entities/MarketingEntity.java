package uca.tpa.concess.entities;

public class MarketingEntity {
    private int age;
    private String sexe;
    private double taux;
    private String situationFamiliale;
    private int nbEnfantsAcharge;
    private boolean deuxiemeVoiture;

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

    public int getNbEnfantsAcharge() {
        return nbEnfantsAcharge;
    }

    public void setNbEnfantsAcharge(int nbEnfantsAcharge) {
        this.nbEnfantsAcharge = nbEnfantsAcharge;
    }

    public boolean isDeuxiemeVoiture() {
        return deuxiemeVoiture;
    }

    public void setDeuxiemeVoiture(boolean deuxiemeVoiture) {
        this.deuxiemeVoiture = deuxiemeVoiture;
    }
}
