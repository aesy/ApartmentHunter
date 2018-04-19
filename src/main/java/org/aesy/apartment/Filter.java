package org.aesy.apartment;

public interface Filter<T> {
    T filter(T elements);
}
