import java.util.ArrayList;

public class Store {
    private static int cnt = 0; // initialized to 0, and will increase by 1 when the constructor is called.
    private int id; // unique for each store and the value is set to cnt.
    private String name;
    private ArrayList<Product> productList = new ArrayList<>();
    private float income;

    public void setIncome(float income) {
        this.income = income;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public Store(String name){
        this.name = name;
        income = 0;
        cnt++;
        this.id = cnt;
    }

    public Store(String name, ArrayList<Product> productList, float income){
        this.name = name;
        this.income = income;
        this.productList.addAll(productList);
        cnt++;
        this.id = cnt;
    }

    public boolean hasProduct(Product product){
        return productList.contains(product);
    }

    public boolean addProduct(Product product){
        if (!hasProduct(product)){
            productList.add(product);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeProduct(Product product){
        if (hasProduct(product)){
            productList.remove(product);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Product> getProductList(){
        return productList;
    }

    public void transact(Product product, int method){
        if (method == 0){
            removeProduct(product);
            income += product.getPrice();
        } else if (method == 1){
            addProduct(product);
            income -= product.getPrice();
        }
    }
}
