package com.jiangy.thinkinginstatemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@RequestMapping("/api")
@RestController
public class TestController {
    @Autowired
    private ProcessService service;

    @GetMapping("/tests")
    public void test() {
        service.startProcess();
    }
}
