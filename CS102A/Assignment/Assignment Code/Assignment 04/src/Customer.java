import java.util.*;

public class Customer {
    private static int cnt = 0; // initialized to 0, and will increase by 1 when the constructor is called.
    private int id; // unique for each customer and the value is set to cnt.
    private String name;
    private ArrayList<Product> shoppingCart = new ArrayList<>(); // The list of purchased products; default is empty.
    private float wallet;
    private HashMap<Product, Store> map = new HashMap<>();
    private static int index = 0;
    private HashMap<Product, Integer> indexs = new HashMap<>();

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    public Customer(String name, float wallet){
        this.name = name;
        this.wallet = wallet;
        cnt++;
        this.id = cnt;
    }

    public boolean rateProduct(Product product, int rating){
        if (rating >= 1 && rating <= 5){
            product.setRating(rating);
            return true;
        } else {
            return false;
        }
    }

    public void updateWallet(float amount){
        wallet += amount;
    }

    public boolean purchaseProduct(Store store, Product product){
        if (store.hasProduct(product) && wallet >= product.getPrice()){
            index++;
            store.transact(product, 0);
            updateWallet(-product.getPrice());
            shoppingCart.add(product);
            map.put(product, store);
            indexs.put(product,index);
            return true;
        } else {
            return false;
        }
    }

    public void viewShoppingCart(SortBy sortMethod){
        if (sortMethod == SortBy.PurchaseTime){
            for (int i = 0; i < shoppingCart.size(); i++){
                System.out.println(shoppingCart.get(i));
            }
        } else if (sortMethod == SortBy.Rating){
            Collections.sort(shoppingCart, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    if ((o1.getAvgRating()-o2.getAvgRating() > 0 ? 1 : o1.getAvgRating()==o2.getAvgRating() ? 0 : -1) != 0){
                        return (o1.getAvgRating()-o2.getAvgRating() > 0 ? 1 : o1.getAvgRating()==o2.getAvgRating() ? 0 : -1);
                    } else {
                        return (indexs.get(o1)-indexs.get(o2) > 0 ? 1 : -1);
                    }
                }
            });
            for (int i = 0; i < shoppingCart.size(); i++){
                System.out.println(shoppingCart.get(i));
            }

        } else if (sortMethod == SortBy.Price){
            Collections.sort(shoppingCart, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    if ((o1.getPrice()-o2.getPrice() > 0 ? 1 : o1.getPrice()==o2.getPrice() ? 0 : -1) != 0){
                        return (o1.getPrice()-o2.getPrice() > 0 ? 1 : o1.getPrice()==o2.getPrice() ? 0 : -1);
                    } else {
                        return (indexs.get(o1)-indexs.get(o2) > 0 ? 1 : -1);
                    }
                }
            });
            for (int i = 0; i < shoppingCart.size(); i++){
                System.out.println(shoppingCart.get(i));
            }
        }
    }

    public boolean refundProduct(Product product){
        if (shoppingCart.contains(product)){
            shoppingCart.remove(product);
            updateWallet(product.getPrice());
            map.get(product).transact(product, 1);
            return true;
        } else {
            return false;
        }
    }

}

