select distinct station_id, chinese_name
from stations
where chinese_name like '%海%'
order by station_id asc
