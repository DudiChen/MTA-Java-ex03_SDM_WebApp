package entity;
import entity.market.MarketUtils;

import java.awt.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;


public class OrderInvoice implements Serializable {

    private OrderStatus orderStatus = OrderStatus.ISSUED;
    private int areaId;
    private int storeId;
    private String storeName;
    private int orderId;
    private int customerId;
    private List<InvoiceProduct> invoiceProducts;
    private List<InvoiceDiscountProduct> discountProducts;
    private double totalPrice;
    private Date deliveryDate;
    private Point deliveryDestination;
    private double shipmentPrice;

    public int getCustomerId() { return this.customerId; }

    public double getShipmentPrice() {
        return shipmentPrice;
    }

    public OrderInvoice(int areaId, int orderId, int customerId, List<InvoiceProduct> invoiceProducts, List<InvoiceDiscountProduct> discountProducts, double totalPrice, Date deliveryDate, int storeId, String storeName, Point deliveryDestination, double shipmentPrice) {
        this.areaId = areaId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.invoiceProducts = invoiceProducts;
        this.discountProducts = discountProducts;
        this.totalPrice = MarketUtils.roundDoublePercision(totalPrice);
        this.deliveryDate = deliveryDate;
        this.storeId = storeId;
        this.storeName = storeName;
        this.deliveryDestination = deliveryDestination;
        this.shipmentPrice = MarketUtils.roundDoublePercision(shipmentPrice);
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

    public Point getDeliveryDestination() {
        return this.deliveryDestination;
    }

    public void setStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<InvoiceProduct> getInvoiceProducts() {
        return invoiceProducts;
    }

    public int getNumberOfInvoiceProducts() {
        return invoiceProducts.stream()
            .map(invoiceProduct -> (int)invoiceProduct.getQuantity())
            .mapToInt(x -> x).sum();
    }

    public List<InvoiceDiscountProduct> getDiscountProducts() {
        return discountProducts;
    }

    public int getNumberOfDiscountProducts() {
        return discountProducts.stream()
                .map(invoiceProduct -> (int)invoiceProduct.getQuantity())
                .mapToInt(x -> x).sum();
    }

    public void setDiscountProducts(List<InvoiceDiscountProduct> value) {
        this.discountProducts = value;
        for (InvoiceDiscountProduct discountProduct : this.discountProducts) {
            this.totalPrice += discountProduct.getAdditionalCost();
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalProductsPrice() {
        double result = invoiceProducts.stream().map(invoiceProduct -> invoiceProduct.getPrice() * invoiceProduct.getQuantity()).reduce(0.0, Double::sum);
        return MarketUtils.roundDoublePercision(result);
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public double getTotalDiscountsPrice() {
        double result = discountProducts.stream().map(InvoiceDiscountProduct::getAdditionalCost).reduce(0.0, Double::sum);
        return MarketUtils.roundDoublePercision(result);
    }

    enum OrderStatus implements Serializable {
        ACCEPTED, CANCELED, ISSUED
    }
}
