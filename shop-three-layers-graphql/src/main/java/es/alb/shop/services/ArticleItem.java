package es.alb.shop.services;

import es.alb.shop.data.ArticleItemEntity;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ArticleItem {
    private Long barcode;
    private Integer amount;
    private BigDecimal discount;

    public ArticleItem() {
        //empty for framework
    }

    public ArticleItem(Long barcode, Integer amount, BigDecimal discount) {
        this.barcode = barcode;
        this.amount = amount;
        this.discount = discount;
    }

    public ArticleItem(ArticleItemEntity articleItemEntity) {
        BeanUtils.copyProperties(articleItemEntity, this);
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setDiscount(String discount) {
        this.discount = new BigDecimal(discount);
    }

    @Override
    public String toString() {
        return "ArticleItem{" +
                "barcode=" + barcode +
                ", amount=" + amount +
                ", discount=" + discount +
                '}';
    }
}
