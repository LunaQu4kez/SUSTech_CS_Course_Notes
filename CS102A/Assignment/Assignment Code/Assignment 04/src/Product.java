import java.util.ArrayList;

public class Product {
    private static int cnt = 0; // initialized to 0, and will increase by 1 when the constructor is called.
    private int id; // unique for each product and the value is set to cnt.
    private String name;
    private float price;
    private ArrayList<Integer> ratings = new ArrayList<>(); // ratings from different customers; default is empty.

    public Product(String name, float price){
        this.name = name;
        this.price = price;
        cnt++;
        this.id = cnt;
    }

    public boolean setRating(int rating){
        if (rating >= 1 && rating <= 5){
            this.ratings.add(rating);
            return true;
        }
        return false;
    }

    public float getAvgRating(){
        int totalRating = 0;
        for (int i = 0; i < this.ratings.size(); i++){
            totalRating += this.ratings.get(i);
        }
        if (ratings.size() == 0){
            return 0;
        }
        return (float) totalRating / this.ratings.size();
    }

    public String toString(){
        return "Product ID " + this.id + ", " + this.name + ", RMB " +
                String.format("%.2f",this.price) + ", Rating " + String.format("%.1f",getAvgRating());
    }

    public float getPrice() {
        return price;
    }

}
