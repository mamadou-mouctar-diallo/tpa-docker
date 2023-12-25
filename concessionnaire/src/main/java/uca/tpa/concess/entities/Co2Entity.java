package uca.tpa.concess.entities;

public class Co2Entity {
    private String modele;
    private String bonusMalus;
    private int rejetsCO2;
    private double coutEnergie;

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getBonusMalus() {
        return bonusMalus;
    }

    public void setBonusMalus(String bonusMalus) {
        this.bonusMalus = bonusMalus;
    }

    public int getRejetsCO2() {
        return rejetsCO2;
    }

    public void setRejetsCO2(int rejetsCO2) {
        this.rejetsCO2 = rejetsCO2;
    }

    public double getCoutEnergie() {
        return coutEnergie;
    }

    public void setCoutEnergie(double coutEnergie) {
        this.coutEnergie = coutEnergie;
    }
}
