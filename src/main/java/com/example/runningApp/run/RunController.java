package com.example.runningApp.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RunController {

  @Autowired
  private RunService runService;

  @GetMapping("/run")
  public List<RunEntity> get() {
    return runService.get();
  }

  @PostMapping("/run")
  public RunEntity save(@RequestBody RunEntity run) {
    runService.save(run);
    return run;
  }

  @GetMapping("/run/{id}")
  public RunEntity get(@PathVariable int id) {
    return runService.get(id);
  }

  @DeleteMapping("/run/{id}")
  public String delete(@PathVariable int id) {
    runService.delete(id);
    return "Run event has been removed with id: " + id;
  }

  @PutMapping("/run")
  public RunEntity updated(@RequestBody RunEntity run) {
    runService.save(run);
    return run;
  }

}
