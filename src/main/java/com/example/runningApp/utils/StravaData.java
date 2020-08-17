package com.example.runningApp.utils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StravaData {

  public static void main(String[] args) {
    String jdbcURL = "jdbc:mysql://localhost:3306/running?useLegacyDatetimeCode=false&serverTimezone=PST";
    String username = "root";
    String password = "NewPassword";

    String file = "activities.csv";

    int batchSize = 20;

    Connection connection = null;

    try {

      connection = DriverManager.getConnection(jdbcURL, username, password);
      connection.setAutoCommit(false);

      String sql = "INSERT INTO events (miles, elevation, hours, minutes, seconds, pace, effort, avg_hr, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(sql);

      BufferedReader lineReader = new BufferedReader(new FileReader(file));
      String line;

      lineReader.readLine();

      int count = 0;

      while ((line = lineReader.readLine()) != null) {
        count++;

        String[] tokens = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        double miles = Double.parseDouble(tokens[3]) * 0.621371;

        double elevation = 0.0;
        String elevationStr  = tokens[8];
        if (!elevationStr.isEmpty()) {
          elevation = Double.parseDouble(tokens[8]);
        }

        Timestamp date = fixDate(tokens[1]);

        int[] times = parseTime(Integer.parseInt(tokens[5]));
        int hours = times[0];
        int minutes = times[1];
        int seconds = times[2];

        double pace = (Integer.parseInt(tokens[5]) / miles) / 60;

        int effort = Integer.parseInt(tokens[4]);

        String hr  = tokens[9];
        int avg_hr = 0;
        if (!hr.isEmpty()) {
          double avg_hr_double = Double.parseDouble(hr);
          avg_hr = (int) Math.round(avg_hr_double);
        }

        statement.setDouble(1, miles);
        statement.setDouble(2, elevation);
        statement.setInt(3, hours);
        statement.setInt(4, minutes);
        statement.setInt(5, seconds);
        statement.setDouble(6, pace);
        statement.setInt(7, effort);
        statement.setInt(8, avg_hr);
        statement.setTimestamp(9, date);

        if (count % batchSize == 0) {
          statement.executeBatch();
        }

        statement.addBatch();

      }

      lineReader.close();

      // execute the remaining queries
      statement.executeBatch();

      connection.commit();
      connection.close();

    } catch (IOException ex) {
      System.err.println(ex);
    } catch (SQLException ex) {
      ex.printStackTrace();

      try {
        connection.rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }

  private static Timestamp fixDate(String date) {

    date = date.substring(1, date.length()-1);

    String[] dateParts = date.split(",");

    String[] day =  dateParts[0].split(" ");

    dateParts[1] = dateParts[1].trim();

    if (dateParts[2].length() == 12) {
      dateParts[2] =  dateParts[2].trim();
      dateParts[2] = "0" + dateParts[2];
    }

    if (day[1].length() == 1) {
      day[1] = "0" + day[1];
    }

    String newDate = day[0] + "-" + day[1] + "-" + dateParts[1] + " " + dateParts[2];

    DateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss a");

    java.util.Date dt;
    try {
      dt = formatter.parse(newDate);
      long time = dt.getTime();
      time = time - 25200000;
      dt.setTime(time);

      java.sql.Timestamp param = new java.sql.Timestamp(dt.getTime());

      return param;

     // return sqlDate;

    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }


  }

  private static int[] parseTime(int time) {
    int[] lst = new int[]{0,0,0};

    int hours = time / 3600;
    time = time - (3600 * hours);
    int minutes = time / 60; //632
    time = time - (60 * minutes);

    lst[0] = hours;
    lst[1] = minutes;
    lst[2] = time;

    return lst;
  }
}