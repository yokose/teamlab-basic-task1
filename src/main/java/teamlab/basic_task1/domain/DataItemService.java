package teamlab.basic_task1.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import teamlab.basic_task1.infrastructure.DataItem;
import teamlab.basic_task1.infrastructure.DataItemRepository;

import java.util.List;

@Service
public class DataItemService {
    private static final Logger logger = LoggerFactory.getLogger(DataItemService.class);
    private final DataItemRepository repository;
    public DataItemService(DataItemRepository repository) {
        this.repository = repository;
    }

    /**
     * serchPassingSetByIdメソッド
     * 引数idのpassingSetを探すメソッド
     * @param id 探したいpassingSetのid(int型)
     * @return Product product 検索したproductを返す
     */
    public Product searchProductById(int id){
        try{
            DataItem dataItem = this.repository.findById(id).orElseThrow(NoItemException::new);
            Product product = new Product(dataItem.getTitle(),dataItem.getDescription(),dataItem.getPrice());
            return product;
        }catch (NoItemException e){
            throw e;
        }
    }

    /**
     * searchDataItemByIdメソッド
     * idからDataItemを探すメソッド
     * @param id
     * @return DataItem dataItem  検索したitemを返す
     */
    public DataItem searchDataItemById(int id){
        try{
            DataItem dataItem = this.repository.findById(id).orElseThrow(NoItemException::new);
            return dataItem;
        }catch (NoItemException e){
            throw e;
        }
    }

    /**
     * repositorySaveメソッド
     * dataItem型をrepositoryに保存する
     * @param dataItem
     */
    public void repositorySave(DataItem dataItem){ this.repository.save(dataItem); }

    /**
     * newDataItemCheckメソッド
     * 新規登録するdataItemが登録可能なデータか判断する
     * @param dataItem
     * @return エラーがあればエラーメッセージをなければnull
     */
    public void dataItemCheck(DataItem dataItem){
        List<DataItem> checkList = repository.findByTitle(dataItem.getTitle());
        if(!CollectionUtils.isEmpty(checkList)){
            throw new SameTitleException();
        }

        return ;
    }

    /**
     * deleteメソッド
     * 指定idのdataItemを削除
     * @param id
     */
    public void dataItemDelete(int id){
        this.repository.delete(searchDataItemById(id));
    }

    /**
     * pictureDELETEメソッド
     * 指定idのpictureデータを削除
     * @param id
     */
    public void pictureDelete(Integer id){
        DataItem dataItem = searchDataItemById(id);
        dataItem.setPicture(null);
        repositorySave(dataItem);
    }

}
