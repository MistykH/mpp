package Repository;

import Domain.Book;
import Domain.Client;
import Domain.Entity;
import Domain.ValidatorException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;

public class InMemoryRepo<ID,T extends Entity<ID>> implements Repository<ID,T> {
    ArrayList<T> entities;

    public InMemoryRepo() {
        this.entities = new ArrayList<>();
    }

    @Override
    public Optional<T> findOne(ID id) throws ValidatorException {
        if(id == null){
            throw new ValidatorException("invalid id");
        }
        Optional<T> b1=Optional.empty();
        for(T b:this.entities){
            if(b.getId()==id)
            {
               b1=Optional.ofNullable(b);
                break;

            }
        }
        if(b1.isEmpty()){
            throw new ValidatorException("doesnt exist");
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
        if(entity == null){
            throw new IllegalArgumentException("null argument");
        }
        boolean found = false;
        for(T Entity : this.entities){
            if(Entity.equals(entity)){
                found = true;
                break;
            }
        }





        if(found == false) {
            this.entities.add(entity);
            return Optional.empty();
        }
        else{
            Optional<T> E = Optional.ofNullable(entity);
            return E;
        }
    }

    @Override
    public Optional<T> delete(ID id) {
        if(id == null){
            throw new IllegalArgumentException("null id");
        }
        for(T entity : this.entities){
            if(entity.getId() == id){
                Optional<T> Entity = Optional.ofNullable(entity);
                this.entities.remove(entity);
                return Entity;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if(entity == null){
            throw new IllegalArgumentException("null entity");
        }
        for(T Entity : this.entities){
            if(Entity.equals(entity)){
                int index = this.entities.indexOf(Entity);
                this.entities.set(index,entity);
                return Optional.empty();
            }
        }
        Optional<T> Entity = Optional.ofNullable(entity);
        return Entity;
    }
}
