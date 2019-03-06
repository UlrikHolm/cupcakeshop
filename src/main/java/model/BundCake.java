package model;

public class BundCake {

    private int bundID;
    private String navnBund;
    private int prisBund;

    public BundCake(int bundID, String navnBund, int prisBund) {
        this.bundID = bundID;
        this.navnBund = navnBund;
        this.prisBund = prisBund;
    }

    public int getBundID() {
        return bundID;
    }

    public void setBundID(int bundID) {
        this.bundID = bundID;
    }

    public String getNavnBund() {
        return navnBund;
    }

    public void setNavnBund(String navnBund) {
        this.navnBund = navnBund;
    }

    public int getPrisBund() {
        return prisBund;
    }

    public void setPrisBund(int prisBund) {
        this.prisBund = prisBund;
    }
}
