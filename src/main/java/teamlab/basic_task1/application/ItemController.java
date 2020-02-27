package teamlab.basic_task1.application;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import teamlab.basic_task1.domain.DataItemService;
import teamlab.basic_task1.infrastructure.DataItem;
import teamlab.basic_task1.domain.Product;

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



    @RequestMapping(value = "/picture", method = RequestMethod.GET)
    @ResponseBody
    public String getPicture(int id){
        String picture = new String();
        return picture;
    }

    @RequestMapping(value = "/picture", method = RequestMethod.POST)
    public void postPicture(int id, String picture){
    }

    @RequestMapping(value = "/picture", method = RequestMethod.PUT)
    public void putPicture(int id, String picture){
    }

    @RequestMapping(value = "/picture", method = RequestMethod.DELETE)
    public void deletePicture(int id){
    }

    /**
     * entryメソッド
     * タイトル、商品説明、価格を登録する
     * @param product
     */
    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public void entry(@RequestBody @Validated Product product, BindingResult result){
        if(result.hasErrors()){
            return ;
        }
        DataItem dataItem = new DataItem();
        dataItem = dataItemService.putProduct2DataItem(product,dataItem);
        dataItem.setId(9L); //のちに修正
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

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(int id){
        dataItemService.dataItemDelete(id);
    }
}
