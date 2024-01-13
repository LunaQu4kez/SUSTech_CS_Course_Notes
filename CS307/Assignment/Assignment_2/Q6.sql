select x.line_id, l.line_color, x.num_of_stricts
from (
    select l.line_id, count(distinct s.district) as num_of_stricts
    from line_detail l join stations s on l.station_id = s.station_id
    group by l.line_id
    order by num_of_stricts desc
    limit 1
) x join lines l on x.line_id = l.line_id
