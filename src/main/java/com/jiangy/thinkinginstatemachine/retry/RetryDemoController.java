package com.jiangy.thinkinginstatemachine.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>创建时间: 2025/3/11 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@RestController
@RequestMapping("/api/retry")
public class RetryDemoController {

    @Autowired
    private RetryDemoService retryDemoService;

    @GetMapping("demo")
    public String test() {
        return retryDemoService.callExternalService();
    }
}
