package pl.sp6pat.ham.hf33wosp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CloudlogHrdContactsLeaderRepository extends CrudRepository<Leader, Long> {

    @Query(
            value = "select COL_CALL, sum(points) as points_sum from ( SELECT COL_CALL, cast(COL_TIME_ON as date), COL_BAND, COL_MODE, CASE WHEN COL_MODE like 'CW' THEN 2 ELSE 1 END as points FROM cloudlog.TABLE_HRD_CONTACTS_V01 WHERE station_id = :stationId AND COL_CALL like :call GROUP BY COL_BAND, cast(COL_TIME_ON as date), COL_BAND, COL_MODE) t group by 1",
            nativeQuery = true)
    Leader calculatePointsByCallNative(@Param("stationId") Integer stationId, @Param("call") String call);

}
