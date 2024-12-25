package pl.sp6pat.ham.hf33wosp.repositories.sc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.sp6pat.ham.hf33wosp.repositories.cl.OperatorPoints;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, ScheduleId> {
    List<Schedule> findByDate(LocalDate date);
}
