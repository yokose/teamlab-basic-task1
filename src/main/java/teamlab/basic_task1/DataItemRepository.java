package teamlab.basic_task1;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;

public interface DataItemRepository extends JpaRepository<DataItem, Long> {
    public List<DataItem> findByTitle(String title);
}
