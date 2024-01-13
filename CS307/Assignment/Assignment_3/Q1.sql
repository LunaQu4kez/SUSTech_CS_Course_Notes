select count(distinct station_id)
from (
    select station_id
    from line_detail
    where station_id in (
        select station_id from line_detail
        where line_id = 3
    ) and station_id not in (
        select station_id from line_detail
        where line_id = 4
    ) and station_id in (
        select station_id
        from (
            select station_id, latitude, avg(latitude) OVER() avg
            from stations
        ) s1
        where s1.latitude > s1.avg
    )
) s2
