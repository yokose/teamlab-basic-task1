package teamlab.basic_task1.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import teamlab.basic_task1.infrastructure.DataItem;
import teamlab.basic_task1.domain.DataItemForm;
import teamlab.basic_task1.infrastructure.DataItemRepository;

@Controller
public class HomeController {

    private final DataItemRepository repository;

    public HomeController(DataItemRepository repository) { this.repository = repository; }

    @ModelAttribute
    DataItem setUpTodoItem() {
        return  new DataItem();
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
}
