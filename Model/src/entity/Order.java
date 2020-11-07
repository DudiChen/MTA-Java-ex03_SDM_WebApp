package entity;

import entity.market.MarketUtils;
import javafx.util.Pair;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class Order {
    List<Pair<Integer, Double>> productIdsToQuantity;
    List<Discount.Offer> offersTaken;
    Point destination;
    Date deliveryDate;
    int areaId;
    int storeId;
    String storeName;
    int customerId;
    int id;

    public Order(int customerId, int areaId, List<Pair<Integer, Double>> productIdsToQuantity, List<Discount.Offer> offersTaken, Point destination, Date deliveryDate, int storeId, String storeName) {
        this.areaId = areaId;
        this.customerId =  customerId;
        this.productIdsToQuantity = productIdsToQuantity;
        this.offersTaken = offersTaken;
        this.destination = destination;
        this.deliveryDate = deliveryDate;
        this.id = this.hashCode();
        this.id = MarketUtils.generateId();
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public int getAreaId() {
        return areaId;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    // TODO: use customerId for InvoiceOrder Conversion and display
    public int getCustomerId() {
        return customerId;
    }

    public List<Pair<Integer, Double>> getProductIdsToQuantity() {
        return productIdsToQuantity;
    }
    // TODO: use getOffersTaken for InvoiceOrder Conversion and display
    public List<Discount.Offer> getOffersTaken() {
        return offersTaken;
    }

    public Point getDestination() {
        return destination;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public int getId() {
        return id;
    }
}
