select distinct line_id from line_detail
where station_id in (
    select station_id from stations
    where chinese_name like '%山%' or chinese_name like '%海%'
)