package observer;

import events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}
