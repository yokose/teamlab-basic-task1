package teamlab.basic_task1.domain;

import org.springframework.web.multipart.MultipartFile;

//view用のpictureクラス
public class PictureForm {
    private MultipartFile picture;

    private String pictureBase64;

    public String getPictureBase64(){ return pictureBase64; }

    public void setPictureBase64(String pictureBase64){ this.pictureBase64 = pictureBase64; }

    public MultipartFile getPicture(){ return picture; }

    public void setPicture(MultipartFile picture){ this.picture = picture; }
}
