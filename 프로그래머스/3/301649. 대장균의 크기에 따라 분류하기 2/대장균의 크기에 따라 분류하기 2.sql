/*
  WITH 절(CTE)을 사용하여 쿼리를 두 단계로 나눕니다.
  1. RANKING_CTE: NTILE(4)를 이용해 각 ID에 4분위 그룹(1~4)을 할당합니다.
  2. 최종 SELECT: 할당된 그룹 번호를 CASE 문으로 이름(COLONY_NAME)으로 변환합니다.
*/
WITH RANKING_CTE AS (
    SELECT 
        ID,
        -- SIZE_OF_COLONY를 내림차순으로 정렬하여
        -- 4개의 그룹(Quartile)으로 나눕니다.
        -- 가장 큰 값이 1, 가장 작은 값이 4가 됩니다.
        NTILE(4) OVER (ORDER BY SIZE_OF_COLONY DESC) AS QuartileGroup
    FROM 
        ECOLI_DATA
)
SELECT 
    ID,
    CASE 
        WHEN QuartileGroup = 1 THEN 'CRITICAL' -- 상위 0% ~ 25% (가장 큰 그룹)
        WHEN QuartileGroup = 2 THEN 'HIGH'     -- 상위 26% ~ 50%
        WHEN QuartileGroup = 3 THEN 'MEDIUM'   -- 상위 51% ~ 75%
        WHEN QuartileGroup = 4 THEN 'LOW'      -- 상위 76% ~ 100% (가장 작은 그룹)
    END AS COLONY_NAME
FROM 
    RANKING_CTE
ORDER BY 
    ID ASC; -- 최종 결과는 ID 기준 오름차순 정렬