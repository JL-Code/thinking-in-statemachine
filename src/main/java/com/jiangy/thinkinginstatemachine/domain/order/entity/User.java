package com.jiangy.thinkinginstatemachine.domain.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

/**
 * <p>创建时间: 2025/3/5 </p>
 * <p>
 * CREATE TABLE user (
 * user_id BIGINT PRIMARY KEY COMMENT '用户ID',
 * -- 押金相关核心字段 --
 * total_deposit DECIMAL(15,2) NOT NULL DEFAULT 0.00 COMMENT '总押金金额（可用+冻结）',
 * available_deposit DECIMAL(15,2) NOT NULL DEFAULT 0.00 COMMENT '可用押金',
 * frozen_deposit DECIMAL(15,2) NOT NULL DEFAULT 0.00 COMMENT '冻结押金',
 * deposit_version INT NOT NULL DEFAULT 0 COMMENT '乐观锁版本号',
 * -- 其他字段 --
 * deposit_currency VARCHAR(3) DEFAULT 'CNY' COMMENT '押金币种',
 * deposit_last_updated DATETIME COMMENT '最后押金变动时间',
 * deposit_status ENUM('NORMAL','LOCKED') DEFAULT 'NORMAL' COMMENT '押金账户状态'
 * ) ENGINE=InnoDB;
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Data
@Entity(name = "sys_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Comment("总押金金额（可用+冻结）")
    private BigDecimal totalDeposit;

    @Comment("可用押金")
    private BigDecimal availableDeposit;

    @Comment("冻结押金")
    private BigDecimal frozenDeposit;

    @Comment("押金乐观锁版本号")
    private Integer depositVersion;

}
