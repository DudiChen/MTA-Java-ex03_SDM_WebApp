package servlet.pojo;

import entity.OrderInvoice;
import servlet.util.ServletUtils;

import java.util.stream.IntStream;

public class ConsumerOrderDTO {

    String id;
    String areaId;
    String storeId;
    String storeName;
    String destination;
//    String numberOfStores;
    String numberOfProducts;
    String productsCost;
    String additionalCosts;
    String deliveryCost;
    String deliveryDate;
    String total;


    public ConsumerOrderDTO(entity.OrderInvoice orderInvoice) {
        this.id = Integer.toString(orderInvoice.getOrderId());
        this.areaId = Integer.toString(orderInvoice.getAreaId());
        this.storeId = Integer.toString(orderInvoice.getStoreId());
        this.storeName = orderInvoice.getStoreName();
        this.destination = String.format("(%d,%d)", (int)orderInvoice.getDeliveryDestination().getX(), (int)orderInvoice.getDeliveryDestination().getY());
//        this.numberOfStores = "1";
        this.numberOfProducts = Integer.toString(orderInvoice.getNumberOfInvoiceProducts());
        this.productsCost = Double.toString(orderInvoice.getTotalProductsPrice());
        this.additionalCosts = Double.toString(orderInvoice.getTotalDiscountsPrice());
        this.deliveryCost = Double.toString(orderInvoice.getShipmentPrice());
        this.deliveryDate = ServletUtils.formatDateToString(orderInvoice.getDeliveryDate());
        this.total = Double.toString(orderInvoice.getTotalPrice());
    }
}
