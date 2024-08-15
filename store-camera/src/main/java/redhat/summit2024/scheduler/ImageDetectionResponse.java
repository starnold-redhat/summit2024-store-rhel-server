package redhat.summit2024.scheduler;

public class ImageDetectionResponse {
    private String known;

    private String pricetag;

    private String[] products;

    private String item;

    public String getItem(){
       return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    public String getKnown() {
        return known;
    }

    public void setKnown(String known) {
        this.known = known;
    }

    public String getPricetag() {
        return pricetag;
    }

    public void setPricetag(String pricetag) {
        this.pricetag = pricetag;
    }

    public String[] getProducts() {
        return products;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }

    
}
