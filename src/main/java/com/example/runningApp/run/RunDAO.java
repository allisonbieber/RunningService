package com.example.runningApp.run;

import java.util.List;


/**
 * Database Access Object
 * Provides logical access to the database
 */
public interface RunDAO {

  List<RunEntity> get();

  RunEntity get(int id);

  void save(RunEntity run);

  void delete(int id);
}
