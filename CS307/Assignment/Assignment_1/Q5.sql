select bus_line, count(station_id) as cnt
from bus_lines
group by bus_line
having count(station_id) >= 10
order by cnt desc