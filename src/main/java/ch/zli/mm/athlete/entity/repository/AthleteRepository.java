package ch.zli.mm.athlete.entity.repository;

import ch.zli.mm.athlete.entity.Athlete;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AthleteRepository extends CrudRepository<Athlete, Integer> {

    Optional<Athlete> findByName(String name);
}
