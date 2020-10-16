package com.test.kafkasagapattern.model.dto;

import com.test.kafkasagapattern.model.enums.HistoryStatus;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class HistoryResponseDTO implements Serializable{

    private Integer id;
    private Integer ordenId;
    private Date fecha;
    private HistoryStatus status;

}
