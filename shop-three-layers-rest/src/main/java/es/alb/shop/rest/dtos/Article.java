package es.alb.shop.rest.dtos;

import es.alb.shop.data.ArticleEntity;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Article {
    private String id;
    private Long barcode;
    private String description;
    private LocalDate registrationDate;
    private BigDecimal price;
    private String provider;

    public Article() {
        //empty for framework
    }

    public Article(Long barcode, String description, BigDecimal price, String provider) {
        this.barcode = barcode;
        this.description = description;
        this.price = price;
        this.provider = provider;
    }

    public Article(ArticleEntity articleEntity) {
        BeanUtils.copyProperties(articleEntity, this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "id='" + id + '\'' +
                ", barcode='" + barcode + '\'' +
                ", description='" + description + '\'' +
                ", registrationDate=" + registrationDate +
                ", price=" + price +
                ", provider='" + provider + '\'' +
                '}';
    }
}
