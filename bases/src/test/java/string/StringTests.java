package string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-09-11
 * @see
 * @since 1.0.0
 */
@Slf4j
public class StringTests {

    @Test
    public void testStr(){
        String variable = "abc";

        StringBuffer rev = new StringBuffer(variable).reverse();

        String strRev = rev.toString();

        //if(variable.equalsIgnoreCase(strRev))
        log.info("{}-{}",strRev,variable);
    }

}
