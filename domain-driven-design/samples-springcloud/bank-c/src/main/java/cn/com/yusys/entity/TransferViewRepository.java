package cn.com.yusys.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-09
 * @see
 * @since 1.0.0
 */
@Repository
public interface TransferViewRepository extends JpaRepository<TransferView,Long> {
}
