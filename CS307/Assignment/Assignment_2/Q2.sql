select
    case
        when latitude is null then
            'No latitude information for ' || stations.english_name || ' station'
        else
            'The latitude of ' || stations.english_name || ' station is: ' || latitude
    end
from stations
where station_id in (
    select station_id from line_detail
    where line_id = 4
)