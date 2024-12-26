#HF33WOSp last heard
SELECT
    COL_MODE,
    COL_BAND,
    COL_FREQ,
    max(COL_TIME_ON) as COL_TIME_ON,
    TIMEDIFF(UTC_TIME(), TIME(max(COL_TIME_ON))) AS TIME_AGO
FROM
    cloudlog.TABLE_HRD_CONTACTS_V01
WHERE
    DATEDIFF(COL_TIME_ON, UTC_DATE()) = 0 AND
    TIME_TO_SEC(TIMEDIFF(UTC_TIME(), TIME(COL_TIME_ON))) < 10000 AND
    station_id = 13
GROUP BY
    COL_MODE, COL_BAND, COL_FREQ
ORDER BY
    COL_TIME_ON DESC
LIMIT
    10
;

# sum of points
select
    COL_CALL, sum(points) as points_sum
from (
         SELECT
             COL_CALL, cast(COL_TIME_ON as date), COL_BAND, COL_MODE, CASE WHEN COL_MODE like 'CW' THEN 2 ELSE 1 END as points
         FROM
             cloudlog.TABLE_HRD_CONTACTS_V01
         WHERE
             station_id = 13 AND COL_CALL like 'sp6pat'
         GROUP BY
             COL_BAND, cast(COL_TIME_ON as date), COL_BAND, COL_MODE
     ) t
group by 1
;



select
    s1_0.date,s1_0."00",s1_0."01",s1_0."02",s1_0."03",s1_0."04",s1_0."05",s1_0."06",s1_0."07",
    s1_0."08",s1_0."09",s1_0."10",s1_0."11",s1_0."12",s1_0."13",s1_0."14",s1_0."15",s1_0."16",
    s1_0."17",s1_0."18",s1_0."19",s1_0."20",s1_0."21",s1_0."22",s1_0."23"
from hf33wosp.schedule_daily s1_0
