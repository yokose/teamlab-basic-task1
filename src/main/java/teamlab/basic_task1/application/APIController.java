package teamlab.basic_task1.application;

import org.springframework.web.bind.annotation.*;
import teamlab.basic_task1.domain.DataItemService;
import teamlab.basic_task1.infrastructure.PassingSet;

/**
 * @author kenshin
 * @version 1.0
 */

@RestController
public class APIController {

   private final DataItemService dataItemService;

    public APIController(DataItemService dataItemService) {
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

    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public void entry(String title,String description,int price){
    }


    /**
     * getメソッド
     * タイトル、商品説明、価格を取得する
     * @param
     * @return passingSet
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public PassingSet get(){
        PassingSet passingSet = dataItemService.searchPassingSetById(1);
        return passingSet;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public void edit(int id,String title,String description,int price){
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(int id){
    }
}
