package teamlab.basic_task1.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teamlab.basic_task1.domain.*;
import teamlab.basic_task1.infrastructure.DataItem;

import java.util.Base64;
import java.util.List;

/**
 * @author kenshin
 * @version 1.0
 */


@RestController
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
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
        DataItem dataItem = new DataItem();
        try{
            dataItem = dataItemService.searchDataItemById(id);
        }catch (NoItemException e){
            return null;
        }

        String encode = Base64.getEncoder().encodeToString(dataItem.getPicture());
        Picture picture = new Picture(encode, null);
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
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                logger.info(String.valueOf(error));
            }
            return ;
        }

        DataItem dataItem = new DataItem();
        try{
            dataItem = dataItemService.searchDataItemById(id);
        }catch (NoItemException e){
            return ;
        }

        byte[] decode = Base64.getDecoder().decode(picture.getPicture());
        dataItem.setPicture(decode);
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
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                logger.info(String.valueOf(error));
            }
            return ;
        }

        DataItem dataItem = new DataItem();
        try{
            dataItem = dataItemService.searchDataItemById(id);
        }catch (NoItemException e){
            return ;
        }
        byte[] decode = Base64.getDecoder().decode(picture.getPicture());
        dataItem.setPicture(decode);

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
    public void entry(@RequestBody @Validated Product product, BindingResult result){
        if(result.hasErrors()){
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                logger.info(String.valueOf(error));
            }
            return ;
        }
        DataItem dataItem = new DataItem();
        dataItem.putProductToDataItem(product);
        try{
            dataItemService.dataItemCheck(dataItem);
        }catch (SameTitleException e){
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
        Product product = null;
        try{
            product = dataItemService.searchProductById(id);
        }catch (NoItemException e){
            return null;
        }
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
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                logger.info(String.valueOf(error));
            }
            return ;
        }

        DataItem dataItem = dataItemService.searchDataItemById(id);
        dataItem.putProductToDataItem(product);
        try{
            dataItemService.dataItemCheck(dataItem);
        }catch (SameTitleException e){
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

}
