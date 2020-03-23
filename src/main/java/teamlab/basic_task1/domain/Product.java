package teamlab.basic_task1.domain;

import javax.validation.constraints.*;
import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Size(min=1, max=100)
    private String title;

    @NotEmpty
    @Size(min=1,max=500)
    private String description;

    @NotNull
    private int price;

    public Product(String title, String description, int price){
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
