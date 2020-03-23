package teamlab.basic_task1.domain;

import teamlab.basic_task1.infrastructure.DataItem;

import java.io.Serializable;
import java.util.List;

public class DataItemForm implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<DataItem> dataItems;

    public List<DataItem> getDataItems() { return dataItems; }

    public void setDataItems(List<DataItem> dataItems) { this.dataItems = dataItems; }
}
