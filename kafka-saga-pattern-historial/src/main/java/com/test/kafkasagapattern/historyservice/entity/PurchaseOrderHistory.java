package com.test.kafkasagapattern.historyservice.entity;

import com.test.kafkasagapattern.model.enums.HistoryStatus;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class PurchaseOrderHistory {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer purchaseId;
    private Date fecha;
    private HistoryStatus status;

}
