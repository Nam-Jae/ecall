package ecall.domain;

import ecall.DispatchApplication;
import ecall.domain.DispatchCanceled;
import ecall.domain.Dispatched;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Dispatch_table")
@Data

public class Dispatch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String carId;

    private String carType;

    private String workerId;

    private String dispatchTime;

    // @PostPersist
    // public void onPostPersist() {
    //     Dispatched dispatched = new Dispatched(this);
    //     dispatched.publishAfterCommit();
    // }

    // @PostRemove
    // public void onPostUpdate(){
    //     DispatchCanceled dispatchCanceled = new DispatchCanceled(this);
    //     dispatchCanceled.publishAfterCommit();
    // }

    public static DispatchRepository repository() {
        DispatchRepository dispatchRepository = DispatchApplication.applicationContext.getBean(
            DispatchRepository.class
        );
        return dispatchRepository;
    }

    public static void getHelp(Called called) {
        Dispatch dispatch = new Dispatch();
        dispatch.setId(called.getId());
        dispatch.setCarId(called.getCarId());
        dispatch.setCarType("tow car");
        dispatch.setWorkerId("workerA");
        dispatch.setDispatchTime("now+1");
        repository().save(dispatch);

        Dispatched dispatched = new Dispatched(dispatch);
        dispatched.publishAfterCommit();
    }

    public static void malfunction(CallCanceled callCanceled) {
        Dispatch dispatch = new Dispatch();
        dispatch.setId(callCanceled.getId());
        dispatch.setCarId(callCanceled.getCarId());
        dispatch.setCarType("tow car");
        dispatch.setWorkerId("workerA");
        dispatch.setDispatchTime("now+1");
        repository().save(dispatch);

        DispatchCanceled dispatchCanceled = new DispatchCanceled(dispatch);
        dispatchCanceled.publishAfterCommit();
    }
}
