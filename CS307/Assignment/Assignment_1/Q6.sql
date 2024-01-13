select station_id, count(distinct bus_line) as cnt
from bus_lines
group by station_id
order by cnt desc
limit 1
