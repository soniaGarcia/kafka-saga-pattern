package com.test.kafkasagapattern.model.evt;

import com.test.kafkasagapattern.model.enums.HistoryStatus;
import java.util.Date;
import lombok.Data;

@Data
public class HistoryEvent {

    private Integer purchaseId;
    private Date fecha;
    private HistoryStatus status;

}
