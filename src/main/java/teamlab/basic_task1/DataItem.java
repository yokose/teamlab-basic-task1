package teamlab.basic_task1;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "items")
public class DataItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String title;
}
