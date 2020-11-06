package servlet.pojo;

public class ProductInNewStoreDTO {
    String id;
    int price;
    String name;

    public ProductInNewStoreDTO(String id, int price, String name) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
