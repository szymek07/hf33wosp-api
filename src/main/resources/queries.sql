#HF33WOSp last heard
SELECT
    COL_MODE,
    COL_BAND,
    COL_FREQ,
    COL_TIME_ON,
    TIMEDIFF(UTC_TIME(), TIME(COL_TIME_ON)) AS TIME_AGO  ,
    TIME_TO_SEC(TIMEDIFF(UTC_TIME(), TIME(COL_TIME_ON)))
FROM
    cloudlog.TABLE_HRD_CONTACTS_V01
WHERE
    DATEDIFF(COL_TIME_ON, UTC_DATE()) = 0 AND
    TIME_TO_SEC(TIMEDIFF(UTC_TIME(), TIME(COL_TIME_ON))) < 5000 AND
    station_id = 13
ORDER BY
    COL_TIME_ON DESC
LIMIT
    10
;


select
    COL_CALL, sum(points) as points_sum
from (
    SELECT
        COL_CALL, COL_MODE, CASE WHEN COL_MODE like 'CW' THEN 2 ELSE 1 END as points
    FROM
        cloudlog.TABLE_HRD_CONTACTS_V01
    WHERE
        station_id = 10 AND COL_CALL like 'sp6pat'
    ) t
group by 1
having sum(points) >= 2
order by 1
;
