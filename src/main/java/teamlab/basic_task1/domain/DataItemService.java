package teamlab.basic_task1.domain;

import org.springframework.stereotype.Service;
import teamlab.basic_task1.infrastructure.DataItem;
import teamlab.basic_task1.infrastructure.DataItemRepository;
import teamlab.basic_task1.infrastructure.PassingSet;

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
     * @param id 探したいpassingSetのid
     * @return PassingSet passingSet 検索したtodoを返す
     */
    public PassingSet searchPassingSetById(Integer intId){
        Long id = Long.valueOf(intId.toString());
        try{
            DataItem dataItem = this.repository.findById(id).orElseThrow(myException::new);
            PassingSet passingSet = new PassingSet(dataItem.getTitle(),dataItem.getDescription(),dataItem.getPrice());
            return passingSet;
        }catch (myException e){
            this.errorMessage = "itemがnullです。";
        }
        return null;
    }
}
