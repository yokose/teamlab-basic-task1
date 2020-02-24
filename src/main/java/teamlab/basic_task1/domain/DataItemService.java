package teamlab.basic_task1.domain;

import org.springframework.stereotype.Service;
import teamlab.basic_task1.infrastructure.DataItem;
import teamlab.basic_task1.infrastructure.DataItemRepository;
import teamlab.basic_task1.infrastructure.Product;

import javax.xml.crypto.Data;

@Service
public class DataItemService {

    public String errorMessage;
    private final DataItemRepository repository;

    public DataItemService(DataItemRepository repository) {
        this.repository = repository;
    }

    /**
     * serchPassingSetByIdメソッド
     * 引数idのpassingSetを探すメソッド
     * @param intId 探したいpassingSetのid(int型)
     * @return PassingSet passingSet 検索したtodoを返す
     */
    public Product searchProductById(Integer intId){
        Long id = Long.valueOf(intId.toString());
        try{
            DataItem dataItem = this.repository.findById(id).orElseThrow(noItemException::new);
            Product product = new Product(dataItem.getTitle(),dataItem.getDescription(),dataItem.getPrice());
            return product;
        }catch (noItemException e){
            this.errorMessage = "itemがnullです。";
        }
        return null;
    }

    public void repositorySave(DataItem item){ this.repository.save(item); }
}
