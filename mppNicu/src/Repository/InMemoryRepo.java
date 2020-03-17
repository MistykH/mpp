package Repository;

import Domain.Book;
import Domain.Client;
import Domain.Entity;
import Domain.ValidatorException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class InMemoryRepo<ID,T extends Entity<ID>> implements Repository<ID,T> {
    ArrayList<T> entities;

    public InMemoryRepo() {
        this.entities = new ArrayList<>();
    }

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }

    @Override
    public Optional<T> findOne(ID id) throws ValidatorException {
        if(id == null){
            throw new ValidatorException("invalid id");
        }
        Optional<T> b1=Optional.empty();

        /*for(T b:this.entities){
            if(b.getId()==id)
            {
               b1=Optional.ofNullable(b);
                break;

            }
        }*/

        b1=Optional.ofNullable(this.entities.stream().filter(b->b.getId()==id).collect(toSingleton()));

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

       /* boolean found = false;
        for(T Entity : this.entities){
            if(Entity.equals(entity)){
                found = true;
                break;
            }
        }*/

       boolean found=this.entities.stream().anyMatch(entity1->entity1.equals(entity));

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

        /*for(T entity : this.entities){
            if(entity.getId() == id){
                Optional<T> Entity = Optional.ofNullable(entity);
                this.entities.remove(entity);
                return Entity;
            }
        }*/

        Optional<T> entityAux=Optional.ofNullable(this.entities.stream().filter(entity1->entity1.getId()==id).collect(toSingleton()));
        this.entities= (ArrayList<T>) this.entities.stream().filter(entity1->entity1.getId()!=id).collect(Collectors.toList());
        return entityAux;

    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if(entity == null){
            throw new IllegalArgumentException("null entity");
        }
       /* for(T Entity : this.entities){
            if(Entity.equals(entity)){
                int index = this.entities.indexOf(Entity);
                this.entities.set(index,entity);
                return Optional.empty();
            }
        }*/

        this.entities.stream().filter(entity1->entity1.equals(entity)).forEach(e->e.setEntity(entity));
        Optional<T> Entity = Optional.ofNullable(entity);
        return Entity;
    }
}
