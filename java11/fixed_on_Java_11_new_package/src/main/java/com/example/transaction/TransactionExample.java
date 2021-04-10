package com.example.transaction;

import jakarta.transaction.TransactionRequiredException;

public class TransactionExample {

    public void transactionalMethod() throws TransactionRequiredException {
        throw new TransactionRequiredException();
    }
}

