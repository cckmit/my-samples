package cn.com.yusys;

import javax.validation.constraints.NotBlank;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-08
 * @see
 * @since 1.0.0
 */
public interface AccountInterface {

    /*@NotBlank
    double getBalance();*/

    @NotBlank
    String getCardNo();

    /*String getFromNO();

    String getToNO();

    double getAmount();*/
}
