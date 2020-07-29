package com.example.runningApp.run;

import com.sun.istack.Nullable;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="run")

/**
 * Defines what the entity 'run'
 */
public class RunEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Integer id;

  @Nullable
  @Column(name="date")
  private Date date;

  @Nullable
  @Column(name="miles")
  private Double miles;

  @Nullable
  @Column(name="elevation")
  private double elevation;

  @Nullable
  @Column(name="hours")
  private int hours;

  @Nullable
  @Column(name="minutes")
  private int minutes;

  @Nullable
  @Column(name="seconds")
  private int seconds;

  @Nullable
  @Column(name="pace")
  private double pace;

  @Nullable
  @Column(name="effort")
  private int effort;

  @Nullable
  @Column(name="max_hr")
  private int max_hr;

  @Nullable
  @Column(name = "max_speed")
  private double max_speed;

  @Override
  public String toString() {
    return "Run id: " + id + ", " + miles + " miles, " + hours +
            " hours, " + minutes + "minutes, and " + seconds + " seconds.";

  }

  public Integer getId() {
    return id;
  }

  // Getters and setters

}