package Models;

import java.io.Serializable;

public  class Purchase implements Serializable {
    private int id, count;
    private double totalcost;
    private String name, discription, imgurl;

    public Purchase(int _id, String _name, String _discription, String _imgurl, int _count, double cost){
        this.id = _id;
        this.name = _name;
        this.discription = _discription;
        this.imgurl = _imgurl;
        this.count = _count;
        this.totalcost = _count * cost;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDiscription() {
        return discription;
    }

    public String getImgurl() {
        return imgurl;
    }

    public int getCount() {
        return count;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public String PStr(){
        return name + ", " + Integer.toString(count) + " крассовок";
    }
}
