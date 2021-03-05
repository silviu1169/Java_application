package repository;

import domain.Entity;

public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends AbstractRepository<ID, E>{
    protected String fileName;

    public AbstractFileRepository(String fileName){
        super();
        this.fileName = fileName;
        loadFromFile();
    }

    public abstract void saveToFile();

    public abstract void loadFromFile();

    @Override
    public void save (E element){
        super.save(element);
        saveToFile();
    }

    @Override
    public void update (E oldElement, E newElement){
        super.update(oldElement, newElement);
        saveToFile();
    }

    @Override
    public E delete (ID id){
        E element = super.delete(id);
        saveToFile();
        return element;
    }

}
