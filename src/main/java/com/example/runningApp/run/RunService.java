package com.example.runningApp.run;

import java.util.List;

/**
 * Used to mark a class as a service provider
 * Defines the business logic away from the controller
 */
public interface RunService {

  List<RunEntity> get();

  RunEntity get(int id);

  void save(RunEntity run);

  void delete(int id);
}
