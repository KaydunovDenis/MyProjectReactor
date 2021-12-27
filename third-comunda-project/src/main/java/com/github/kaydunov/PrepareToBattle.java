package com.github.kaydunov;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class PrepareToBattle implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        int enemyWarriors = (int) (Math.random() * 100);
        String battleStatus = "Undefined";

//        delegateExecution.setVariable();
    }
}
