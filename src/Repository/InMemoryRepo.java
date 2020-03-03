package Repository;

import Domain.Book;
import Domain.Entity;
import Domain.ValidatorException;

import java.util.ArrayList;
import java.util.Optional;

public class InMemoryRepo<ID,T extends Entity<ID>> implements Repository<ID,T> {
    ArrayList<T> entities;

    public InMemoryRepo() {
        this.entities = new ArrayList<>();
    }

    @Override
    public Optional<T> findOne(ID id) {
        Optional<T> b1=Optional.empty();
        for(T b:this.entities){
            if(b.getId()==id)
            {
               b1=Optional.ofNullable(b);
                break;

            }
        }

        return b1;

        //TODO throw ex

    }

    @Override
    public Iterable<T> findAll() {
        return this.entities;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        return Optional.empty();
    }

    @Override
    public Optional<T> delete(ID id) {
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        return Optional.empty();
    }
}
