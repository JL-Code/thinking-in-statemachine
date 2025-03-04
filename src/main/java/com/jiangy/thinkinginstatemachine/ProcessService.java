package com.jiangy.thinkinginstatemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Service
public class ProcessService {
    @Autowired
    private StateMachine<States, Events> stateMachine;

    public void startProcess() {
        stateMachine.sendEvent(Events.E1);
    }
}

