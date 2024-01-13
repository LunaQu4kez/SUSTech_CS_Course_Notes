select s.station_id, s.chinese_name, x1.ld1id, x1.ld2id
from stations s join (
    select distinct station_id, 1 as ld1id, 5 as ld2id
    from line_detail
    where station_id in (
        select station_id from line_detail
        where line_id = 1
    ) and station_id in (
        select station_id from line_detail
        where line_id = 5
    )
    union
    select distinct station_id, 1, 11
    from line_detail
    where station_id in (
        select station_id from line_detail
        where line_id = 1
    ) and station_id in (
        select station_id from line_detail
        where line_id = 11
    )
    union
    select distinct station_id, 5, 11
    from line_detail
    where station_id in (
        select station_id from line_detail
        where line_id = 5
    ) and station_id in (
        select station_id from line_detail
        where line_id = 11
    )
) x1 on s.station_id = x1.station_id
