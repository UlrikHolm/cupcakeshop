package model;

public class TopCake {

    private int topID;
    private String navnTop;
    private int prisTop;

    public TopCake(int topID, String navnTop, int prisTop) {
        this.topID = topID;
        this.navnTop = navnTop;
        this.prisTop = prisTop;
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

    public int getPrisTop() {
        return prisTop;
    }

    public void setPrisTop(int prisTop) {
        this.prisTop = prisTop;
    }
}
