package teamlab.basic_task1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    private final DataItemRepository repository;

    public HomeController(DataItemRepository repository) { this.repository = repository; }

    @ModelAttribute
    DataItem setUpTodoItem() {
        return  new DataItem();
    }

    @ModelAttribute
    DataItemFrom setUpTodoDataItemFrom() {
        DataItemFrom dataItemFrom = new DataItemFrom();
        dataItemFrom.setDataItems(this.repository.findAll());
        dataItemFrom.setCheck("kkk");

        return dataItemFrom;
    }

    @RequestMapping
    public String index(@ModelAttribute DataItemFrom dataItemForm) {
        dataItemForm.setDataItems(this.repository.findAll());
        dataItemForm.setCheck("kkk");

        return "index";
    }
}
