package repository;

import domain.Entity;

public interface Repository<ID, E extends Entity <ID>> {
    public void save (E element);
    public E findOne(ID id);
    public void update (E oldElement, E newElement);
    public E delete (ID id);
    public Iterable<E> findAll();
}
