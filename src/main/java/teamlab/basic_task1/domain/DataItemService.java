package teamlab.basic_task1.domain;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import teamlab.basic_task1.infrastructure.DataItem;
import teamlab.basic_task1.infrastructure.DataItemRepository;

import java.util.List;

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
     * @param dataItemId 探したいpassingSetのid(int型)
     * @return Product product 検索したproductを返す
     */
    public Product searchProductById(Integer dataItemId){
        Long id = Long.valueOf(dataItemId.toString());
        try{
            DataItem dataItem = this.repository.findById(id).orElseThrow(NoItemException::new);
            Product product = new Product(dataItem.getTitle(),dataItem.getDescription(),dataItem.getPrice());
            return product;
        }catch (NoItemException e){
            this.errorMessage = "itemがnullです。";
        }
        return null;
    }

    /**
     * searchDataItemByIdメソッド
     * idからDataItemを探すメソッド
     * @param dataItemId
     * @return DataItem dataItem  検索したitemを返す
     */
    public DataItem searchDataItemById(Integer dataItemId){
        Long id = Long.valueOf(dataItemId.toString());
        try{
            DataItem dataItem = this.repository.findById(id).orElseThrow(NoItemException::new);
            return dataItem;
        }catch (NoItemException e){
            this.errorMessage = "itemがnullです." ;
        }
        return null;
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
            errorMessage = ("同じタイトルがあります。");
            return ;
        }
        return ;
    }

    /**
     * putProduct2DataItem
     * Product型をDataItem型に挿入
     * @param product
     * @param dataItem
     * @return 挿入したDataItem型
     */
    public DataItem putProduct2DataItem(Product product,DataItem dataItem){
        dataItem.setTitle(product.getTitle());
        dataItem.setDescription(product.getDescription());
        dataItem.setPrice(product.getPrice());
        return dataItem;
    }

    /**
     * deleteメソッド
     * 指定idのdataItemを削除
     * @param dataItemId
     */
    public void dataItemDelete(Integer dataItemId){
        Long id = Long.valueOf(dataItemId.toString());
        this.repository.deleteById(id);
    }



}
