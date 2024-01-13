select station_id
from bus_lines
where bus_line = '103'
union
select station_id
from bus_lines
where bus_line = '104'
order by station_id desc


