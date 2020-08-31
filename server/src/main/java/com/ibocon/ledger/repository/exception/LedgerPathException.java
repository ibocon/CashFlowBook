package com.ibocon.ledger.repository.exception;

public class LedgerPathException extends Exception  {

    public LedgerPathException(String message) { super(message); }
    public LedgerPathException(String message, Throwable cause) {
        super(message, cause);
    }
}
