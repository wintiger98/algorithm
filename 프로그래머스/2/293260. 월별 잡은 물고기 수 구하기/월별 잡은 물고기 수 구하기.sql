-- 코드를 작성해주세요
select 
      COUNT(*) as FISH_COUNT
    , MONTH(TIME) as MONTH
from fish_info
group by MONTH(TIME)
having COUNT(*) <> 0
order by month
;