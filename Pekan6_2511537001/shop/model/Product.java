package shop.model;

public abstract class Product {

    private final String sku;
    private final String nama;
    private final long price;

    protected Product(String sku, String nama, long price) {
        if (sku == null || sku.isBlank()){
            throw new IllegalArgumentException("SKU Must Not Blank");
        }
        if (nama == null || nama.isBlank()){
            throw new IllegalArgumentException("Nama Must Not Blank");
        }
        if (price < 0 ){
            throw new IllegalArgumentException("Price Must Not Be Negative");
        }
        this.sku = sku;
        this.nama = nama;
        this.price = price;
    }

    public String getsku() { return sku; }
    public String getnama() { return nama; }
    public long getPrice() { return price; }

    public long getFinalPrice() {return price;}
    public abstract String category();
    public String getInfo(){
        return "[" + category() + "] " + nama + " - Rp" + getFinalPrice(); 
    }

}