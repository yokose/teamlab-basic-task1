package teamlab.basic_task1.domain;

import java.io.Serializable;

public class Picture implements Serializable {
    private static final long serialVersionUID = 1L;

    private String picture;

    private String box;

    public Picture(String picture, String box){
        this.picture = picture;
        this.box = box;
    }

    public String getPicture() { return picture; }

    public void setPicture(String picture) { this.picture = picture; }

    public String getBox() { return box; }

    public void setBox(String box) { this.box = box; }

}