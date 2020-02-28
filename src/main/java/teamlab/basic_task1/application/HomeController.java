package teamlab.basic_task1.application;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import teamlab.basic_task1.domain.DataItemService;
import teamlab.basic_task1.domain.PictureForm;
import teamlab.basic_task1.infrastructure.DataItem;
import teamlab.basic_task1.domain.DataItemForm;
import teamlab.basic_task1.infrastructure.DataItemRepository;

@Controller
public class HomeController {

    private final DataItemRepository repository;
    private final DataItemService dataItemService;

    public HomeController(DataItemRepository repository, DataItemService dataItemService) {
        this.repository = repository;
        this.dataItemService = dataItemService;
    }

    @ModelAttribute
    DataItem setUpTodoItem() {
        return  new DataItem();
    }

    @ModelAttribute
    PictureForm setUpPictureForm(){
        PictureForm pictureForm = new PictureForm();
        return pictureForm;
    }

    @ModelAttribute
    DataItemForm setUpTodoDataItemForm() {
        DataItemForm dataItemForm = new DataItemForm();
        dataItemForm.setDataItems(this.repository.findAll());

        return dataItemForm;
    }

    @RequestMapping
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/picturetest", method = RequestMethod.POST)
    public String postPicture(PictureForm pictureForm) throws Exception{
        StringBuffer data = new StringBuffer();
        String base64 = new String(Base64.encodeBase64(pictureForm.getPicture().getBytes()),"ASCII");
        data.append("data:image/jpeg;base64,");
        data.append(base64);
        pictureForm.setPictureBase64(data.toString());

        return "express";
    }
}
