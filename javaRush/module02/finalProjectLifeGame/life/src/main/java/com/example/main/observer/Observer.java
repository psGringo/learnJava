package com.example.main.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Observer {
    private HashMap<ObserverEvent, List<Listener>> listeners;

    public Observer() {
        listeners = new HashMap<>();
    }

    public void subscribe(ObserverEvent event, Listener listener) {
        if (listeners.containsKey(event)) {
            var list = listeners.get(event);
            list.add(listener);
        } else {
            List<Listener> list = new ArrayList<>();
            list.add(listener);
            listeners.put(event, list);
        }
    }

    public void unsubscribe(ObserverEvent event, Listener listener) {
        if (listeners.containsKey(event)) {
            var list = listeners.get(event);
            list.remove(listener);
        }
    }

    public void notify(ObserverEvent event, Object data) {
        if (listeners.containsKey(event)) {
            var concreteEventListeners = listeners.get(event);
            concreteEventListeners.forEach(listenable -> {
                listenable.listen(data);
            });
        }
    }

}
