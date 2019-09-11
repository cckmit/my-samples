package cn.com.yusys.es.uuid;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * snowflake 为Twitter推出的满足分布式系统中全局唯一且趋势递增的 ID 64bit，64位递增id比uuid更短且更利于创建索引
 * <ul>
 *     <li>第一位永远是0，实际上这是为了让生成的 ID 都为正数，以保证趋势递增；</li>
 *     <li>后面 41 位用来记录时间，理论上可以记录 2^41 毫秒，2^41/(24 * 3600 * 365 * 1000) = 69.7 年，所以这里的理论最大使用时间就是 70 年左右；</li>
 *     <li>在后面 10 位用来记录机器 ID ，更准确的应该说是实例 ID，对应的可以是某个 Container 或者某个进程，最多支持 1024 个；</li>
 *     <li>最后12位用来记录序列号，来保证每个实例每毫秒生成的 ID 唯一；</li>
 * </ul>
 *
 * @author  yangzq80@gmail.com
 * @date 2019-08-02
 * @since Common Annotations 1.0
 * @see javax.annotation.PreDestroy
 * @see javax.annotation.Resource
 */
@Slf4j
public class SnowFlake {

    private static class TimeBackwardsException extends RuntimeException {
        TimeBackwardsException(String message) {
            super(message);
        }
    }

    /**
     * 起始的时间戳
     */
    private static final long START_STAMP = 1262275200000L;

    /**
     * 每一部分占用的位数
     */
    private static final long SEQUENCE_BIT = 12; //序列号占用的位数
    private static final long MACHINE_BIT = 10;   //机器标识占用的位数

    /**
     * 每一部分的最大值
     */
    public static final long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private static final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    private static final long TIMESTAMP_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    private long machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastStamp = -1L;//上一次时间戳

    SnowFlake(long machineId) {

        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     */
    public synchronized Long nextId() {
        long currStamp = getNewStamp();
        if (currStamp < lastStamp) {
            throw new TimeBackwardsException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStamp == lastStamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;

            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                sequence = new Random().nextInt(10);
                currStamp = getNextMill();
            }
        } else {
            // 新的一毫秒，随机从 0-9 中开始
            sequence = new Random().nextInt(10);
        }
        lastStamp = currStamp;

        return (currStamp - START_STAMP) << TIMESTAMP_LEFT //时间戳部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }

        return mill;
    }

    private long getNewStamp() {
        return System.currentTimeMillis();
    }

}

