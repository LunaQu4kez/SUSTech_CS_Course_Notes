select z.start, z.terminal, z.line_id, w.line_color, w.hex
from (
    select y.line_id as line_id, y.chinese_name as start, s2.chinese_name as terminal
    from ((
        select line_id, min(station_id) as min, max(station_id) as max
        from line_detail
        group by line_id
        ) x
    join stations s1 on x.min = s1.station_id) y
    join stations s2 on y.max = s2.station_id
) z join (
    select line_id, line_color, hex
    from lines l join color_names c on l.line_color = c.name
) w on z.line_id = w.line_id
order by z.line_id asc;



select y.line_id, y.chinese_name, s2.chinese_name
from ((
    select line_id, min(station_id) as min, max(station_id) as max
    from line_detail
    group by line_id
) x
    join stations s1 on x.min = s1.station_id) y
    join stations s2 on y.max = s2.station_id;


select line_id, line_color, hex
from lines l join color_names c on l.line_color = c.name