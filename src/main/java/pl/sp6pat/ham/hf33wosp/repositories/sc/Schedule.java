package pl.sp6pat.ham.hf33wosp.repositories.sc;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(schema = "hf33wosp", name = "schedule_daily")
//@IdClass(ScheduleId.class)
public class Schedule {

    @Id
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "00")
    private String h00;

    @Column(name = "01")
    private String h01;

    @Column(name = "02")
    private String h02;

    @Column(name = "03")
    private String h03;

    @Column(name = "04")
    private String h04;

    @Column(name = "05")
    private String h05;

    @Column(name = "06")
    private String h06;

    @Column(name = "07")
    private String h07;

    @Column(name = "08")
    private String h08;

    @Column(name = "09")
    private String h09;

    @Column(name = "10")
    private String h10;

    @Column(name = "11")
    private String h11;

    @Column(name = "12")
    private String h12;

    @Column(name = "13")
    private String h13;

    @Column(name = "14")
    private String h14;

    @Column(name = "15")
    private String h15;

    @Column(name = "16")
    private String h16;

    @Column(name = "17")
    private String h17;

    @Column(name = "18")
    private String h18;

    @Column(name = "19")
    private String h19;

    @Column(name = "20")
    private String h20;

    @Column(name = "21")
    private String h21;

    @Column(name = "22")
    private String h22;

    @Column(name = "23")
    private String h23;
}
