package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParserFactory;
import pojo.User;

import java.util.Map;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-23
 * @see
 * @since 1.0.0
 */
@Slf4j
public class Json {

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        User user = new User(1L,"zhangsan");

        String jsonStr = mapper.writeValueAsString(user);

        log.info("POJO to JSON --> {}",jsonStr);

        Map map = JsonParserFactory.getJsonParser().parseMap(jsonStr);

        log.info(" JSON str parser to map --> {}",map);
    }
}
