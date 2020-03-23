package teamlab.basic_task1.infrastructure;

import org.springframework.web.multipart.MultipartFile;
import teamlab.basic_task1.domain.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "items")
public class DataItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    @Size(min=1, max=100)
    private String title;

    private byte[] picture;

    @NotEmpty
    @Size(min=1,max=500)
    private String description;

    @NotNull
    private Integer price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title = title; }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

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

    public void putProductToDataItem(Product product){
        this.setTitle(product.getTitle());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
    }
}
