package com.exchangeBE.exchange.exception;

public class InsufficientExchangeMoneyException extends RuntimeException {
    public InsufficientExchangeMoneyException(String message) {
        super(message);
    }
}
