package com.txtbits.orders.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Count {

    long name;
    long count;

    public Count(long name, long count) {
        this.name = name;
        this.count = count;
    }
}
