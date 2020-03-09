package teamlab.basic_task1.application;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import teamlab.basic_task1.domain.DataItemService;
import teamlab.basic_task1.domain.Picture;
import teamlab.basic_task1.domain.PictureForm;
import teamlab.basic_task1.infrastructure.DataItem;
import teamlab.basic_task1.domain.Product;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author kenshin
 * @version 1.0
 */


@RestController
public class ItemController {

    private String errorMessage;

    private final DataItemService dataItemService;
    public ItemController(DataItemService dataItemService) {
        this.dataItemService = dataItemService;
    }

    /**
     * getPictureメソッド
     * idで指定された商品の画像データを取得する
     * @param id
     * @return picture 画像のString型
     */
    @RequestMapping(value = "/picture", method = RequestMethod.GET)
    @ResponseBody
    public Picture getPicture(@RequestParam int id){
        DataItem dataItem = dataItemService.searchDataItemById(id);
        if(!StringUtils.isEmpty(dataItemService.errorMessage)){
            System.out.println(dataItemService.errorMessage);
            dataItemService.errorMessage = null;
            return null;
        }
        Picture picture = new Picture(dataItem.getPicture(), null);
        return picture;
    }

    /**
     * postPictureメソッド
     * idで指定された商品の画像データを登録する
     * @param id
     * @param picture
     * @param result
     */
    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    public void postPicture(@RequestParam("id") int id, @RequestBody @Validated Picture picture, BindingResult result){
        if(result.hasErrors()){
            return ;
        }

        DataItem dataItem = dataItemService.searchDataItemById(id);
        dataItem.setPicture(picture.getPicture());

        dataItemService.pictureCheck(picture.getPicture());
        if(!StringUtils.isEmpty(dataItemService.errorMessage)){
            System.out.println(dataItemService.errorMessage);
            dataItemService.errorMessage = null;
            return ;
        }

        dataItemService.repositorySave(dataItem);
    }

    /**
     * putPictureメソッド
     * idで指定された商品の画像データを編集する
     * @param id
     * @param picture
     * @param result
     */
    @RequestMapping(value = "/picture", method = RequestMethod.PUT)
    public void putPicture(@RequestParam("id") int id, @RequestBody @Validated Picture picture, BindingResult result){
        if(result.hasErrors()){
            return ;
        }

        DataItem dataItem = dataItemService.searchDataItemById(id);
        dataItem.setPicture(picture.getPicture());

        dataItemService.pictureCheck(picture.getPicture());
        if(!StringUtils.isEmpty(dataItemService.errorMessage)){
            System.out.println(dataItemService.errorMessage);
            dataItemService.errorMessage = null;
            return ;
        }

        dataItemService.repositorySave(dataItem);
    }

    /**
     * deletePictureメソッド
     * idで指定された商品の画像データを削除する
     * @param id
     */
    @RequestMapping(value = "/picture", method = RequestMethod.DELETE)
    public void deletePicture(int id){
        dataItemService.pictureDelete(id);
    }

    /**
     * entryメソッド
     * タイトル、商品説明、価格を登録する
     * @param product
     */
    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public void entry(@RequestBody @Valid Product product, BindingResult result, Locale locale){
        if(result.hasErrors()){
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error);
                //logger.info(messageSource.getMessage(error, locale));
            }
            return ;
        }
        DataItem dataItem = new DataItem();
        dataItem = dataItemService.putProduct2DataItem(product,dataItem);
        dataItemService.dataItemCheck(dataItem);
        if(!StringUtils.isEmpty(dataItemService.errorMessage)){
            System.out.println(dataItemService.errorMessage);
            dataItemService.errorMessage = null;
            return ;
        }

        dataItemService.repositorySave(dataItem);
    }

    /**
     * getメソッド
     * タイトル、商品説明、価格を取得する
     * @param id 取得した商品のid
     * @return product 商品データ
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Product get(@RequestParam("id") int id){
        Product product = dataItemService.searchProductById(id);
        return product;
    }

    /**
     * editメソッド
     * タイトル、商品説明、価格を編集する
     * @param id
     * @param product
     * @param result
     */
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public void edit(@RequestParam("id") int id,@RequestBody @Validated Product product, BindingResult result){
        if(result.hasErrors()){
            return ;
        }

        DataItem dataItem = dataItemService.searchDataItemById(id);
        dataItem = dataItemService.putProduct2DataItem(product,dataItem);
        dataItemService.dataItemCheck(dataItem);
        if(!StringUtils.isEmpty(dataItemService.errorMessage)){
            System.out.println(dataItemService.errorMessage);
            dataItemService.errorMessage = null;
            return ;
        }
        dataItemService.repositorySave(dataItem);
    }

    /**
     * deleteメソッド
     * idに該当する商品データを削除する
     * @param id
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(int id){
        dataItemService.dataItemDelete(id);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Book handleException(HttpMessageNotReadableException ex,
                                WebRequest request) {
        Throwable t = ex.getCause();
        if (t != null && t instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) t;
            // エラーのフィールド。
            for (JsonMappingException.Reference r : ife.getPath()) {
                System.out.println(r.getFieldName());
            }
            // エラーになったフィールドの型
            System.out.println("type= " + ife.getTargetType().getName());
            // エラーになったフィールドの値
            System.out.println("value=" + ife.getValue());
        }

        return new Book();
    }
}
