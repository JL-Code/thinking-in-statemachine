package com.jiangy.thinkinginstatemachine.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * <p>创建时间: 2025/3/11 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Service
public class RetryDemoService {

    @Autowired
    private ExternalService externalService;

    @Retryable(
            retryFor = {RuntimeException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000)
    )
    public String callExternalService() {
        return externalService.call();
    }

    @Recover
    public String recover(RuntimeException e) {
        // 处理重试失败后的逻辑
        return "Fallback response";
    }

    @Service
    static class ExternalService {
        public String call() {
            // 模拟外部服务调用
            if (Math.random() < 0.7) {
                throw new RuntimeException("Random failure");
            }
            return "Success";
        }
    }
}
