package com.example.runningApp.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class RunServiceImpl implements RunService {

  @Autowired
  private RunRepository runRepository;


  @Transactional
  @Override
  public List<RunEntity> get() {
    return runRepository.get();
  }

  @Override
  public RunEntity get(int id) {
    return runRepository.get(id);
  }

  @Override
  public void save(RunEntity run) {
    runRepository.save(run);
  }

  @Override
  public void delete(int id) {
    runRepository.delete(id);
  }
}
