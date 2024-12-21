package pl.sp6pat.ham.hf33wosp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CloudlogHrdContactsLeaderRepository extends CrudRepository<Leader, Long> {

    //TODO: zapytanie do poprawy
    @Query(
//            value = "select COL_CALL, sum(points) as points_sum from ( SELECT COL_CALL, COL_MODE, CASE WHEN COL_MODE like 'CW' THEN 2 ELSE 1 END as points FROM cloudlog.TABLE_HRD_CONTACTS_V01 WHERE station_id = :stationId AND COL_CALL like :call) t group by 1 having sum(points) >= 2 order by 1",
            value = "select COL_CALL, sum(points) as points_sum from ( SELECT COL_CALL, COL_MODE, CASE WHEN COL_MODE like 'CW' THEN 2 ELSE 1 END as points FROM cloudlog.TABLE_HRD_CONTACTS_V01 WHERE station_id = :stationId AND COL_CALL like :call) t group by 1 order by 1",
            nativeQuery = true)
    Leader calculatePointsByCallNative(@Param("stationId") Integer stationId, @Param("call") String call);

}
