package pl.sp6pat.ham.hf33wosp.repositories.sc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, ScheduleId> {
    List<Schedule> findByDateGreaterThanEqual(LocalDate date);
}
