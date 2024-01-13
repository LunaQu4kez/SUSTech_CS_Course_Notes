select station_id, chinese_name from stations
where station_id in (
    select station_id from line_detail
    where line_id = 1
) and station_id in (
    select station_id from line_detail
    where line_id = 5
) and station_id not in (
    select station_id from line_detail
    where line_id = 2
)
order by station_id asc
