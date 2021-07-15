package ch.zli.mm.athlete.entity.repository;

import ch.zli.mm.athlete.entity.Athlete;
import ch.zli.mm.athlete.entity.Sport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends CrudRepository<Sport, Integer> {

}
