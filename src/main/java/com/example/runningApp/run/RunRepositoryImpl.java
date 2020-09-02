package com.example.runningApp.run;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RunRepositoryImpl implements RunRepository {

  @Autowired
  private EntityManager entityManager;

  @Override
  public List<RunEntity> get() {
  //  String q = "SELECT miles, elevation, hours, minutes, seconds, pace, effort, avg_hr, date FROM RunEntity";
    String q = "FROM RunEntity";
    Session currSession = entityManager.unwrap(Session.class);
    Query<RunEntity> query = currSession.createQuery(q, RunEntity.class);
    return query.getResultList();
  }

  @Override
  public RunEntity get(int id) {
    Session currSession = entityManager.unwrap(Session.class);
    return currSession.get(RunEntity.class, id);
  }

  @Override
  public void save(RunEntity run) {
    Session currSession = entityManager.unwrap(Session.class);
    currSession.saveOrUpdate(run);
  }

  @Override
  public void delete(int id) {
    Session currSession = entityManager.unwrap(Session.class);
    RunEntity run = currSession.get(RunEntity.class, id);
    currSession.delete(run);
  }

}
