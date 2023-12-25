package uca.tpa.constant;

public enum FileName {
    CLIENT("clients"),
    MARKETING("MARKETING"),

    IMMATRICULATION("immatriculation"),
    CO2("co2"),

    CATALOGUE("catalogue");

    private String code;

    FileName(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
