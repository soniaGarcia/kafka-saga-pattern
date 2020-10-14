package com.test.kafkasagapattern.model.dto;

import com.test.kafkasagapattern.model.enums.HistoryStatus;
import java.util.Date;
import lombok.Data;

@Data
public class HistoryResponseDTO {

    private Integer id;
    private Integer ordenId;
    private Date fecha;
    private HistoryStatus status;

}
