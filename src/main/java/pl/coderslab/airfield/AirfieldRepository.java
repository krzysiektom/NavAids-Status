package pl.coderslab.airfield;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.owner.Owner;

interface AirfieldRepository extends JpaRepository<Airfield, Long> {
    Airfield findByOwner(Owner owner);
}
