package com.jiangy.thinkinginstatemachine.domain.order.repository;

import com.jiangy.thinkinginstatemachine.domain.order.entity.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>创建时间: 2025/3/4 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Repository
public interface DemandRepository extends JpaRepository<Demand, Long> {
}
