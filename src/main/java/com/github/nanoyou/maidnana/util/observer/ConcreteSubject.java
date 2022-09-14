package com.github.nanoyou.maidnana.util.observer;

import java.util.*;

public class ConcreteSubject implements Subject {

    private final List<Observer> observers = Collections.synchronizedList(new LinkedList<>());

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
