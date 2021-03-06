package entity.market;

import entity.*;

import java.util.*;
import java.util.stream.Collectors;

public class Market {
    private Map<Integer, Area> idToArea;
    private Map<Integer, Customer> idToCustomer;

    public Market() {
        this.idToCustomer = new HashMap<>();
        this.idToArea = new HashMap<>();
    }

    public void addArea(Area newArea) {
        idToArea.put(newArea.getId(), newArea);
    }

    public Area getAreaById(int areaId) {
        return this.idToArea.get(areaId);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(this.idToCustomer.values());
    }

    public Customer getCustomerById(int id) { return this.idToCustomer.get(id); }

    public boolean isEmpty() {
        return (idToArea == null || idToCustomer.isEmpty()) ;
    }

    public boolean addCustomer(Customer customer) {
        if(this.getCustomerById(customer.getId()) != null) {
            return false;
        }
        else {
            this.idToCustomer.put(customer.getId(), customer);
            return true;
        }
    }

    public List<Area> getAllAreas() {
        return new ArrayList<>(idToArea.values());
    }

    public double getAreaAverageOrderCost(int areaId) {
        return getAreaById(areaId).getAllStores().stream()
                .map(store -> store.getOrdersHistory())
                .flatMap(list -> list.stream())
                .map(orderInvoice -> orderInvoice.getTotalProductsPrice())
                .mapToDouble(x -> x)
                .average()
                .orElse(0);
    }

    public double getAreaAverageProductPrice(int areaId, int productId) {
        return getAreaById(areaId).getAllStores().stream()
                .filter(store -> store.isProductSold(productId))
                .map(store -> store.getPriceOfProduct(productId))
                .mapToDouble(x -> x)
                .average()
                .orElse(0);
    }

    public int getNumberOfStoresThatSellProduct(int areaId, int productId) {
        return this.getAreaById(areaId).getAllStores().stream()
                .map(store -> store.isProductSold(productId) ? 1 : 0)
                .mapToInt(x -> x)
                .sum();
    }

    public double getBalanceByCustomerId(int uuid) {
        return getCustomerById(uuid).getBalance();
    }

    public Product getAreaProductById(int areaId, int productId) {
        return this.getAreaById(areaId).getProductById(productId);
    }

    public StoreProduct getStoreProductById(int areaId, int storeId, int productId) {
        return this.idToArea.get(areaId).getStoreById(storeId).getSoldProductById(productId);
    }

    public Discount getStoreDiscountByName(int areaId, int storeId, String discountName) {
        return this.getAreaById(areaId).getStoreById(storeId).getDiscountByName(discountName);
    }

    public boolean isCustomerExistByName(String username) {
        return this.idToCustomer.values().stream().anyMatch(customer -> customer.getName().equals(username));
    }

    public boolean isAreaExistsByName(String areaName) {
        return this.idToArea.values().stream().anyMatch(area -> area.getName().equals(areaName));
    }

    public Customer getStoreOwner(int areaId, int storeId) {
        String ownerName = this.getAreaById(areaId).getStoreById(storeId).getOwnerName();
        return this.idToCustomer.values().stream().filter(customer -> customer.getName().equals(ownerName)).findFirst().orElse(null);
    }

    public void chargePurchase(Customer consumer, Customer owner, double amount, Date date) {
        consumer.addTransaction(new Transaction(Transaction.TransactionType.EXPENSE, amount, date, consumer, owner));
        owner.addTransaction(new Transaction(Transaction.TransactionType.EARNING, amount, date, consumer, owner));
    }

    public List<OrderInvoice> getOrdersByCustomerId(int uuid) {
        return this.idToArea.values().stream()
                .map(area -> area.getOrdersHistory())
                .flatMap(orders -> orders.stream())
                .filter(orderInvoice -> orderInvoice.getCustomerId() == uuid)
                .collect(Collectors.toList());
    }
}
