package com.example.runningApp.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class RunServiceImpl implements RunService {

  @Autowired
  private RunDAO runDAO;


  @Transactional
  @Override
  public List<RunEntity> get() {
    return runDAO.get();
  }

  @Override
  public RunEntity get(int id) {
    return runDAO.get(id);
  }

  @Override
  public void save(RunEntity run) {
    runDAO.save(run);
  }

  @Override
  public void delete(int id) {
    runDAO.delete(id);
  }
}
