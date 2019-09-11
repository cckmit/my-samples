package com.examples;

import javax.validation.constraints.NotBlank;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-07
 * @see
 * @since 1.0.0
 */
public interface PaymentInterface {
    @NotBlank
    String getName();

    @NotBlank
    double getAmount();

}
