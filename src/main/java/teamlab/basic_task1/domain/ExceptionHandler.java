package teamlab.basic_task1.domain;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import teamlab.basic_task1.application.ItemController;

import java.awt.print.Book;

@RestControllerAdvice
public class ExceptionHandler extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Book handleException(HttpMessageNotReadableException ex,
                                WebRequest request) {
        Throwable t = ex.getCause();
        if (t != null && t instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) t;
            // エラーのフィールド。
            for (JsonMappingException.Reference r : ife.getPath()) {
                System.out.println(r.getFieldName());
            }
            // エラーになったフィールドの型
            System.out.println("type= " + ife.getTargetType().getName());
            // エラーになったフィールドの値
            System.out.println("value=" + ife.getValue());
        }

        return new Book();
    }
}
