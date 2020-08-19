package com.example.runningApp.run;

import com.sun.istack.Nullable;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="events")

/**
 * Defines what the entity 'run' ocntains
 */
public class RunEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Integer id;

  @Nullable
  @Column(name="date")
  private Timestamp date;

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
  private Integer effort;

  @Nullable
  @Column(name="avg_hr")
  private Integer max_hr;

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
