SELECT 
    ID,
    FISH_NAME,
    LENGTH
FROM
    FISH_INFO A
    JOIN 
        FISH_NAME_INFO B 
    ON 
        A.FISH_TYPE = B.FISH_TYPE
WHERE (A.FISH_TYPE, LENGTH) IN (
    SELECT FISH_TYPE, MAX(LENGTH)
    FROM FISH_INFO
    GROUP BY FISH_TYPE
)
ORDER BY ID