public class lineItem {
    String type;
    int quantity;
    double unitPrice;
    double lineTotal;
    public lineItem (String itemType, int itemQuantity, double itemUnitPrice){
        type=itemType;
        quantity=itemQuantity;
        unitPrice=itemUnitPrice;
        lineTotal=quantity*unitPrice;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }
}
