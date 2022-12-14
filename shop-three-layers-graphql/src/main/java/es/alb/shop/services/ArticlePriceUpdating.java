package es.alb.shop.services;

import java.math.BigDecimal;

public class ArticlePriceUpdating {

    private Long barcode;
    private BigDecimal price;

    public ArticlePriceUpdating() {
        //empty for framework
    }

    public ArticlePriceUpdating(Long barcode, BigDecimal price) {
        this.barcode = barcode;
        this.price = price;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPrice(String price) {
        this.price = new BigDecimal(price);
    }

    @Override
    public String toString() {
        return "ArticlePriceUpdating{" +
                "barcode=" + barcode +
                ", price=" + price +
                '}';
    }
}
