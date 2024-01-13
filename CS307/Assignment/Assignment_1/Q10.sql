select bus_line, count(station_id) as count
from bus_lines
where bus_line similar to 'N[0-9]*'
group by bus_line
