package ecall.domain;

import ecall.domain.*;
import ecall.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Dispatched extends AbstractEvent {

    private Long id;
    private String carId;
    private String carType;
    private String workerId;
    private String dispatchTime;
}
