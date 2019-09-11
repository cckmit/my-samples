package pojo;

import lombok.Data;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-23
 * @see
 * @since 1.0.0
 */
@Data
public class User {
    long id;
    String name;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
