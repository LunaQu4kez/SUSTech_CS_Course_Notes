select bus_line, count(station_id) as count
from bus_lines
group by bus_line