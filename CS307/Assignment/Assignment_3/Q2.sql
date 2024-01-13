select district, count(station_id) as cnt
from (
    select english_name, district, station_id, first
    from stations s natural join (
        select first, count(english_name) as cnt
        from (
            select english_name, substr(english_name, 1, 1) as first
            from stations
            ) s1
        group by first
        order by cnt desc
        limit 1
    ) x1
    where english_name like '%' || upper(first) || '%' or english_name like '%' || lower(first) || '%'
) x2
where district <> ''
group by district
order by cnt desc
