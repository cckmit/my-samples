package cn.com.yusys.entity;

import cn.com.yusys.TransferInterface;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-09
 * @see
 * @since 1.0.0
 */
@Entity
@Data
public class TransferView implements TransferInterface {
    @Id
    @Column(length = 64)
    private Long id;

    String fromNO;
    String toNO;
    Double transferAmount;

    boolean deleted = false;
}
