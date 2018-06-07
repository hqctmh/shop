package cn.mh.util;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

public class BaseService {
    protected void rollback() {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
