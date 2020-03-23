package teamlab.basic_task1.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoItemException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(NoItemException.class);

    public NoItemException(){
        logger.info("itemがnullです。");
    }
}
