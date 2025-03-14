package maxim.spring.restapp.repositories;

import maxim.spring.restapp.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    int countByRainingIsTrue();
}
