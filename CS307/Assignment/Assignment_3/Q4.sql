select opening, 'line ' || line_id || ' opened' as event
from lines
where opening is not null
and line_id in (
    select distinct line_id from line_detail
) and 2008 <= opening and opening <= 2021

union all

select latest_extension, 'line ' || line_id || ' extended' as event
from lines
where lines.latest_extension is not null
and line_id in (
    select distinct line_id from line_detail
) and 2008 <= latest_extension and latest_extension <= 2021
order by opening
