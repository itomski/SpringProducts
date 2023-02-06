package de.lubowiecki.sproducts.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

// JavaBean Konvention
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue // Spring gesteuertes Autoincrement
    private long id;

    @Column(length = 120)
    @NotEmpty(message = "{validate.string.min}")
    @Size(min = 0, max = 100, message = "{validate.string.max}")
    private String name;

    @Column(length = 800)
    @Size(min = 2, max = 800)
    private String description;

    @FutureOrPresent
    //@DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime availableAt;

    @Positive
    private double price;

    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(String name, String description, double price, Category category, LocalDateTime availableAt) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.availableAt = availableAt;
    }

    public LocalDateTime getAvailableAt() {
        return availableAt;
    }

    public void setAvailableAt(LocalDateTime availableAt) {
        this.availableAt = availableAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceDE() {
        DecimalFormat priceFmt = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMANY);
        priceFmt.setMaximumFractionDigits(2);
        return priceFmt.format(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", availableAt=").append(availableAt);
        sb.append(", price=").append(price);
        sb.append(", category=").append(category);
        sb.append('}');
        return sb.toString();
    }
}
