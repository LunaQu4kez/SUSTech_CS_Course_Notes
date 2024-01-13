select district, round((max(latitude) - min(latitude)) * 111) as 南北跨度km
from stations
where district != ''
group by district
