package cn.com.yusys.es.continuance.producer.jpa;

public interface AggregateSequenceNumberInterface {

    String getAggregateIdentifier();

    Long getSequenceNumber();
}
