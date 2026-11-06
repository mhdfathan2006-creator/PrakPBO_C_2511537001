package shop.model;

public class PhysicalProduct extends Product implements shop.shipping.Shippable {
    private final int weightGrams;

    public PhysicalProduct(String sku, String nama, long price, int weightGrams) {
        super(sku, nama, price);
        if (weightGrams <= 0){
            throw new IllegalArgumentException("Weight Must Not Be Negative");
        }
        this.weightGrams = weightGrams;
    }

    @Override 
    public int getweightGrams() {return weightGrams;}

    public String category() {
        return "Fisik";
    }
}