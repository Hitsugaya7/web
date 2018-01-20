package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class PurchaseList implements Serializable {
    private ArrayList<Purchase> purchases;

    private int totalCost;

    public PurchaseList(){
        purchases = new ArrayList<>();
        totalCost = 0;
    }

    public void addPurchase(Purchase purchase) {
        if (purchase.getCount() > 0){
            for (Purchase item : purchases) {
                if (item.getId() == purchase.getId()) {
                    item = purchase;

                    this.recalculateTotalCost();
                    return;
                }
            }
        this.purchases.add(purchase);
        this.recalculateTotalCost();
        }
    }

    public double getTotalCost() {
        return totalCost;
    }

    private void recalculateTotalCost(){
        if(purchases==null) return;
        this.totalCost=0;

        for (Purchase purchase: this.purchases) {
            totalCost += purchase.getTotalcost();
        }
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
    }

    public String getStr(PurchaseList list){
        String result = "";
        for (Purchase item : list.purchases) {
            result += item.PStr() + " | ";
        }

        if(result != "")
            result += "итог: " + Double.toString(list.getTotalCost()) + "$";

        return result;
    }
}
