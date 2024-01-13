select line_id, chinese_name
from line_detail l join stations s on l.station_id = s.station_id
where s.station_id in (
    select station_id from bus_lines
    where bus_line = '2'
)
order by line_id asc, l.station_id asc