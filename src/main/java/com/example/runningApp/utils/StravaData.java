package com.example.runningApp.utils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

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

      String sql = "INSERT INTO events (id, date, miles, location, elevation, hours, minutes, seconds) VALUES (?, ?, ?, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(sql);

      BufferedReader lineReader = new BufferedReader(new FileReader(file));
      String line;

      lineReader.readLine();

      while ((line = lineReader.readLine()) != null) {

        String[] tokens = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        Date date = fixDate(tokens[1]);
        int[] times = parseTime(Integer.parseInt(tokens[2]));
        int hours = times[0];
        int minutes = times[1];
        int seconds = times[2];
        double miles = Double.parseDouble(tokens[3]) * 0.621371;

        statement.setDate(1, date);

        System.out.println(miles);


       // System.out.println(line);


      }

    //  Scanner scanner = new Scanner(new File(file));

      /*lineReader.readLine(); // skip header line

      while ((lineText = lineReader.readLine()) != null) {
        String[] data = lineText.split(",");
        String courseName = data[0];
        String studentName = data[1];
        String timestamp = data[2];
        String rating = data[3];
        String comment = data.length == 5 ? data[4] : "";

        statement.setString(1, courseName);
        statement.setString(2, studentName);

        Timestamp sqlTimestamp = Timestamp.valueOf(timestamp);
        statement.setTimestamp(3, sqlTimestamp);

        Float fRating = Float.parseFloat(rating);
        statement.setFloat(4, fRating);

        statement.setString(5, comment);

        statement.addBatch();

        if (count % batchSize == 0) {
          statement.executeBatch();
        }
      }

      lineReader.close();*/

      // execute the remaining queries
    //  statement.executeBatch();

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

  private static Date fixDate(String date) {

    date = date.substring(1, date.length()-1);
    System.out.println("before: " + date);

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

      return dt;

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