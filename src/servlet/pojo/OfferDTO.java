package servlet.pojo;

import controller.Controller;
import entity.Discount;

public class OfferDTO {
    String productId;
    String productName;
    double additionalCost;
    double quantity;

    public OfferDTO(Discount.Offer offer, int areaId) {
        // TODO: Need different workaround to get productName of offer
        this.productId = Integer.toString(offer.getProductId());
        this.productName = Controller.getInstance().getAreaProductById(areaId, offer.getProductId()).getName();
        this.additionalCost = offer.getForAdditional();
        this.quantity = offer.getQuantity();
    }
}
