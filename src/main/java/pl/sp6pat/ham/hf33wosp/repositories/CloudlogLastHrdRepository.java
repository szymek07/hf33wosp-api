package pl.sp6pat.ham.hf33wosp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CloudlogLastHrdRepository extends CrudRepository<LastHeard, Long> {

    @Query(
            value = "SELECT " +
                    " COL_MODE, " +
                    " COL_BAND, " +
                    " COL_FREQ, " +
                    " max(COL_TIME_ON) as COL_TIME_ON, " +
                    " TIMEDIFF(UTC_TIME(), TIME(max(COL_TIME_ON))) AS TIME_AGO " +
                    "FROM " +
                    " cloudlog.TABLE_HRD_CONTACTS_V01 " +
                    "WHERE " +
                    " DATEDIFF(COL_TIME_ON, UTC_DATE()) = 0 AND " +
                    " TIME_TO_SEC(TIMEDIFF(UTC_TIME(), TIME(COL_TIME_ON))) < :diffInSec AND " +
                    " station_id = :stationId " +
                    "GROUP BY " +
                    " COL_MODE, COL_BAND, COL_FREQ " +
                    "ORDER BY " +
                    " COL_TIME_ON DESC " +
                    "LIMIT " +
                    " :lim",
            nativeQuery = true)
    List<LastHeard> findLastHeardNative(@Param("stationId") Integer stationId, @Param("diffInSec") Integer diffInSec, @Param("lim") Integer lim);

}
