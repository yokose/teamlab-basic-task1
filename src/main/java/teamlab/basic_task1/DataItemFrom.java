package teamlab.basic_task1;

import java.io.Serializable;
import java.util.List;

public class DataItemFrom implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<DataItem> dataItems;

    private String check;

    public String getCheck() { return check;}

    public void setCheck(String check) { this.check = check; }

    public List<DataItem> getDataItems() { return dataItems; }

    public void setDataItems(List<DataItem> dataItems) { this.dataItems = dataItems; }
}
