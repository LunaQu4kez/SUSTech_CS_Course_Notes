select line_id, opening, cnt, round(cast(x1 as numeric)/cast(x2 as numeric)*100, 2) || '%' as variation
from (
    select l.line_id, opening, cnt,
           cnt - lag(cnt) over (order by opening) as x1, lag(cnt) over (order by opening) as x2
    from lines l join (
        select line_id, max(num) as cnt
        from line_detail
        group by line_id
    ) s1 on l.line_id = s1.line_id
) s2
