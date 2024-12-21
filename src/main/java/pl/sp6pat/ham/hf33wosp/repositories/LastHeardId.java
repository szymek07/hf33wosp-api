package pl.sp6pat.ham.hf33wosp.repositories;

import lombok.Data;

import java.io.Serializable;

@Data
public class LastHeardId implements Serializable {
    private String mode;
    private String band;
    private Long freq;
    private String timeOn;
    private String timeAgo;
}
