package com.bkash.savings.models.postgres.rps.dto;

import com.bkash.savings.models.postgres.rps.RpsTaskQStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RpsTaskQueueMessage {
    private String rppSubReqId;
    private String taskId;
    private String status;
    private RpsTaskQStatus qStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate terminationDate;
    private String rppSubId;
    private LocalDate lastReqTime;
    private String traceId;
}
