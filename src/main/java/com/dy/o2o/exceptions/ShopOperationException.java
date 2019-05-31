package com.dy.o2o.exceptions;

import java.io.Serializable;

public class ShopOperationException extends RuntimeException {

    private static final long serialVersionUID = -2338020374999801687L;

    public ShopOperationException(String message) {
        super(message);
    }
}
