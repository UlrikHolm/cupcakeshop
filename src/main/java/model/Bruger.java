package model;

public class Bruger {

    private int brugerID;
    private int brugerType;
    private String email;
    private String kodeord;
    private int saldo;

    public Bruger() {
    }

    public Bruger(int brugerID, int brugerType, String email, String kodeord, int saldo) {
        this.brugerID = brugerID;
        this.brugerType = brugerType;
        this.email = email;
        this.kodeord = kodeord;
        this.saldo = saldo;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

    public int getBrugerType() {
        return brugerType;
    }

    public void setBrugerType(int brugerType) {
        this.brugerType = brugerType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKodeord() {
        return kodeord;
    }

    public void setKodeord(String kodeord) {
        this.kodeord = kodeord;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
