package clinic.springframework.petclinic.repositories;

import clinic.springframework.petclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
