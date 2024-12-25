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

//    @Id
    @Column(name = "00")
    private String h00;

//    @Id
    @Column(name = "01")
    private String h01;

//    @Id
    @Column(name = "02")
    private String h02;

//    @Id
    @Column(name = "03")
    private String h03;

//    @Id
    @Column(name = "04")
    private String h04;

//    @Id
    @Column(name = "05")
    private String h05;

//    @Id
    @Column(name = "06")
    private String h06;

//    @Id
    @Column(name = "07")
    private String h07;

//    @Id
    @Column(name = "08")
    private String h08;

//    @Id
    @Column(name = "09")
    private String h09;

//    @Id
    @Column(name = "10")
    private String h10;

//    @Id
    @Column(name = "11")
    private String h11;

//    @Id
    @Column(name = "12")
    private String h12;

//    @Id
    @Column(name = "13")
    private String h13;

//    @Id
    @Column(name = "14")
    private String h14;

//    @Id
    @Column(name = "15")
    private String h15;

//    @Id
    @Column(name = "16")
    private String h16;

//    @Id
    @Column(name = "17")
    private String h17;

//    @Id
    @Column(name = "18")
    private String h18;

//    @Id
    @Column(name = "19")
    private String h19;

//    @Id
    @Column(name = "20")
    private String h20;

//    @Id
    @Column(name = "21")
    private String h21;

//    @Id
    @Column(name = "22")
    private String h22;

//    @Id
    @Column(name = "23")
    private String h23;

}
