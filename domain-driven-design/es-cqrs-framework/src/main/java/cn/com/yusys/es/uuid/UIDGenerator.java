package cn.com.yusys.es.uuid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * The class description.
 *
 * @author yangzq80@gmail.com
 * @date 2019-08-08
 * @see
 * @since 1.0.0
 */
@Component
public class UIDGenerator {
    private final WorkerIdService workerIdService;
    private SnowFlake flake;

    @Autowired
    public UIDGenerator(WorkerIdService workerIdService) {
        this.workerIdService = workerIdService;
    }

    public Long getId() {
        return flake.nextId();
    }

    @PostConstruct
    public void init() {
        this.flake = new SnowFlake(workerIdService.getWorkerId());
    }
}
