package com.mcp.semi.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class TransactionLoggingAspect {

    private String formatSignature(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String simpleClassName = className.substring(className.lastIndexOf('.') + 1);
        return simpleClassName + "." + methodName;
    }

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logTransactionStart(JoinPoint joinPoint) {
        log.info("[트랜잭션 시작] {}", formatSignature(joinPoint));
    }

    @AfterReturning("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logTransactionCommit(JoinPoint joinPoint) {
        log.info("[트랜잭션 커밋] {}", formatSignature(joinPoint));
    }

    @AfterThrowing(pointcut = "@annotation(org.springframework.transaction.annotation.Transactional)", throwing = "ex")
    public void logTransactionRollback(JoinPoint joinPoint, Throwable ex) {
        log.info("[트랜잭션 롤백] {} [예외] {}", formatSignature(joinPoint), ex.getMessage());
    }

    @After("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logTransactionEnd(JoinPoint joinPoint) {
        log.info("[트랜잭션 종료] {}", formatSignature(joinPoint));
    }
}
