package pl.sp6pat.ham.hf33wosp.repositories;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Entity
@Data
@IdClass(LastHeardId.class)
public class LastHeard {

    @Id
    @Column(name = "COL_MODE")
    private String mode;

    @Id
    @Column(name = "COL_BAND")
    private String band;

    @Id
    @Column(name = "COL_FREQ")
    private Long freq;

    @Id
    @Column(name = "COL_TIME_ON")
    private String timeOn;

    @Id
    @Column(name = "TIME_AGO")
    private String timeAgo;


}
