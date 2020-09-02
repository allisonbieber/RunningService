package com.example.runningApp.run;

import com.fasterxml.jackson.annotation.JsonView;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.text.View;

@Entity
@Table(name="events")

/**
 * Defines what the entity 'run' contains
 */
public class RunEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(RunEntity.class)
  @Column(name="id")
  private Integer id;

  @JsonView(RunEntity.class)
  @Column(name="date")
  private Timestamp date;

  @JsonView(RunEntity.class)
  @Column(name="miles")
  private Double miles;

  @JsonView(RunEntity.class)
  @Column(name="elevation")
  private double elevation;

  @JsonView(RunEntity.class)
  @Column(name="hours")
  private int hours;

  @JsonView(RunEntity.class)
  @Column(name="minutes")
  private int minutes;

  @JsonView(RunEntity.class)
  @Column(name="seconds")
  private int seconds;

  @JsonView(RunEntity.class)
  @Column(name="pace")
  private double pace;

  @JsonView(RunEntity.class)
  @Column(name="effort")
  private Integer effort;

  @JsonView(RunEntity.class)
  @Column(name="avg_hr")
  private Integer avg_hr;

  @Override
  public String toString() {
    return "Run id: " + id + ", " + miles + " miles, " + hours +
            " hours, " + minutes + "minutes, and " + seconds + " seconds.";

  }

  // Getters
  public Integer getId() {
    return id;
  }

}
