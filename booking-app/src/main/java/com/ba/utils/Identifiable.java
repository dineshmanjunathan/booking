package com.ba.utils;
import java.io.Serializable;


public interface Identifiable<T extends Serializable> {

    T getId();
    T getPrefix();
}
