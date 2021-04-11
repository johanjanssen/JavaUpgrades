package com.example.transaction;

import javax.transaction.TransactionRequiredException;

public class TransactionExample {

    public void transactionalMethod() throws TransactionRequiredException {
        throw new TransactionRequiredException();
    }
}
