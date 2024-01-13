select count(station_id) as max
from bus_lines
group by bus_line
order by max desc
limit 1