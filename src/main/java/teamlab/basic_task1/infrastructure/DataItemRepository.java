package teamlab.basic_task1.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataItemRepository extends JpaRepository<DataItem, Long> {
    public List<DataItem> findByTitle(String title);

}
