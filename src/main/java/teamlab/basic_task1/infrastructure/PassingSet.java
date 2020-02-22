package teamlab.basic_task1.infrastructure;

import java.io.Serializable;

public class PassingSet implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;

    private String description;

    private int price;

    public PassingSet(String title, String description, int price){
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
