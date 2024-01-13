select station_id
from bus_lines
where bus_line = '103'
intersect
select station_id
from bus_lines
where bus_line = '104'
