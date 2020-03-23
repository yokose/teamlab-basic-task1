package teamlab.basic_task1.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SameTitleException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(SameTitleException.class);

    public SameTitleException(){ logger.info("同じタイトルがあります。"); }
}
