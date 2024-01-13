select distinct s.station_id, chinese_name
from stations s join line_detail ld on s.station_id = ld.station_id
where s.station_id in (
    select station_id from line_detail
    where line_id = 1
) and s.station_id in (
    select station_id from line_detail
    where line_id = 5
) and s.station_id not in (
    select station_id from line_detail
    where line_id = 2
)
order by station_id asc
