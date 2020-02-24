package teamlab.basic_task1.application;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import teamlab.basic_task1.domain.DataItemService;
import teamlab.basic_task1.infrastructure.Product;

/**
 * @author kenshin
 * @version 1.0
 */


@RestController
public class ItemController {

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
    public void edit(int id,String title,String description,int price){
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(int id){
    }
}
