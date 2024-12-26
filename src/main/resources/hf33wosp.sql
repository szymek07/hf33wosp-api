CREATE TABLE hf33wosp.schedule (
    id SERIAL PRIMARY KEY,
    call text NOT NULL,
    band text NOT NULL,
    mode text NOT NULL,
    date DATE NOT NULL,
    hour INT NOT NULL CHECK (hour >= 0 AND hour <= 23),
    submitted_at TIMESTAMP NOT NULL,
    form_mode text,
    UNIQUE (band, mode, date, hour)
);


create or replace view hf33wosp.schedule_daily as
SELECT
    date,
    STRING_AGG(CASE WHEN hour = 0 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "00",
    STRING_AGG(CASE WHEN hour = 1 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "01",
    STRING_AGG(CASE WHEN hour = 2 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "02",
    STRING_AGG(CASE WHEN hour = 3 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "03",
    STRING_AGG(CASE WHEN hour = 4 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "04",
    STRING_AGG(CASE WHEN hour = 5 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "05",
    STRING_AGG(CASE WHEN hour = 6 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "06",
    STRING_AGG(CASE WHEN hour = 7 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "07",
    STRING_AGG(CASE WHEN hour = 8 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "08",
    STRING_AGG(CASE WHEN hour = 9 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "09",
    STRING_AGG(CASE WHEN hour = 10 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "10",
    STRING_AGG(CASE WHEN hour = 11 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "11",
    STRING_AGG(CASE WHEN hour = 12 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "12",
    STRING_AGG(CASE WHEN hour = 13 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "13",
    STRING_AGG(CASE WHEN hour = 14 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "14",
    STRING_AGG(CASE WHEN hour = 15 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "15",
    STRING_AGG(CASE WHEN hour = 16 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "16",
    STRING_AGG(CASE WHEN hour = 17 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "17",
    STRING_AGG(CASE WHEN hour = 18 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "18",
    STRING_AGG(CASE WHEN hour = 19 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "19",
    STRING_AGG(CASE WHEN hour = 20 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "20",
    STRING_AGG(CASE WHEN hour = 21 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "21",
    STRING_AGG(CASE WHEN hour = 22 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "22",
    STRING_AGG(CASE WHEN hour = 23 THEN band || ' ' || mode || ' (' || upper(call) || ')' ELSE NULL END, ', ') AS "23"
FROM hf33wosp.schedule
GROUP BY date
ORDER BY date;