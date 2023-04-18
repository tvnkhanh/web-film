package ptit.wibulord.webfilm.model;

public class RevenuePremium {
    int quantity;
    long revenue;

    public RevenuePremium() {
    }

    public RevenuePremium(int quantity, long revenue) {
        this.quantity = quantity;
        this.revenue = revenue;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int id) {
        this.quantity = id;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }
}
