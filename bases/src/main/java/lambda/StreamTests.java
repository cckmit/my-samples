package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-26
 * @see
 * @since 1.0.0
 */
public class StreamTests {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        list.add("a");
        list.add("b");

        String type = "b";

        List<String> results = list.stream().filter(m->{
            //System.out.println(m);
            return m.equalsIgnoreCase(type);
        }).collect(Collectors.toList());

        results.stream().forEach(m ->{
            System.out.println(m);
        });

    }
}
