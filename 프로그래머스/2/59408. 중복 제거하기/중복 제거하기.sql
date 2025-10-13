-- 코드를 입력하세요
SELECT      count(NAME) count
  FROM      (
            SELECT  DISTINCT NAME
            FROM    ANIMAL_INS
            )
;