package com.example.runningApp.run;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Database Access Object
 * Provides logical access to the database
 */
public interface RunRepository {

  List<RunEntity> get();

  RunEntity get(int id);

  void save(RunEntity run);

  void delete(int id);
}
