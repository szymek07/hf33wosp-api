package pl.sp6pat.ham.hf33wosp.repositories.cl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class OperatorPoints {

    @Id
    @Column(name = "COL_CALL")
    private String call;

    @Column(name = "points_sum")
    private Integer points;


}
