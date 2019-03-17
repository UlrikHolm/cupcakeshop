package model;

public class KurveLinje {

    private int kurvelinjeID;
    private String brugerID;
    private int bundID;
    private String navnBund;
    private int topID;
    private String navnTop;
    private int antal;
    private int prisIalt;

    public KurveLinje() {
    }

    public KurveLinje(int kurvelinjeID, String brugerID, int bundID, String navnBund, int topID, String navnTop, int antal, int prisIalt) {
        this.kurvelinjeID = kurvelinjeID;
        this.brugerID = brugerID;
        this.bundID = bundID;
        this.navnBund = navnBund;
        this.topID = topID;
        this.navnTop = navnTop;
        this.antal = antal;
        this.prisIalt = prisIalt;
    }

    @Override
    public String toString() {
        return "KurveLinje{" +
                "kurvelinjeID=" + kurvelinjeID +
                ", brugerID='" + brugerID + '\'' +
                ", bundID=" + bundID +
                ", topID=" + topID +
                ", antal=" + antal +
                ", prisIalt=" + prisIalt +
                '}';
    }

    public int getKurvelinjeID() {
        return kurvelinjeID;
    }

    public void setKurvelinjeID(int kurvelinjeID) {
        this.kurvelinjeID = kurvelinjeID;
    }

    public String getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(String brugerID) {
        this.brugerID = brugerID;
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

    public int getTopID() {
        return topID;
    }

    public void setTopID(int topID) {
        this.topID = topID;
    }

    public String getNavnTop() {
        return navnTop;
    }

    public void setNavnTop(String navnTop) {
        this.navnTop = navnTop;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public int getPrisIalt() {
        return prisIalt;
    }

    public void setPrisIalt(int prisIalt) {
        this.prisIalt = prisIalt;
    }
}
