/*
  WITH 절(CTE)을 사용하여 가독성을 높입니다.
  먼저 GRADE 'A', 'B', 'C'를 구분하는 데 필요한 
  Python, C#, Front End 스킬 코드를 미리 계산합니다.
*/
WITH REQUIRED_SKILLS AS (
    SELECT
        -- 'A' 등급 조건 1: Python 스킬 코드
        (SELECT CODE FROM SKILLCODES WHERE NAME = 'Python') AS PYTHON_CODE,
        
        -- 'A'/'C' 등급 조건: Front End 카테고리 스킬 코드의 총합
        (SELECT SUM(CODE) FROM SKILLCODES WHERE CATEGORY = 'Front End') AS FRONT_END_CODE,
        
        -- 'B' 등급 조건: C# 스킬 코드
        (SELECT CODE FROM SKILLCODES WHERE NAME = 'C#') AS CSHARP_CODE
)
/*
  위에서 계산한 스킬 코드를 바탕으로
  각 개발자의 등급을 CASE 문으로 부여합니다.
*/
, GRADED_DEVELOPERS AS (
    SELECT 
        CASE 
            /* GRADE A: Python과 Front End 스킬을 모두 보유
              (SKILL_CODE & PYTHON_CODE) > 0 AND (SKILL_CODE & FRONT_END_CODE) > 0
            */
            WHEN (D.SKILL_CODE & S.PYTHON_CODE) > 0 AND (D.SKILL_CODE & S.FRONT_END_CODE) > 0 
            THEN 'A'
            
            /* GRADE B: C# 스킬을 보유
              (SKILL_CODE & CSHARP_CODE) > 0
            */
            WHEN (D.SKILL_CODE & S.CSHARP_CODE) > 0 
            THEN 'B'
            
            /* GRADE C: 그 외 Front End 스킬 보유
              (SKILL_CODE & FRONT_END_CODE) > 0
            */
            WHEN (D.SKILL_CODE & S.FRONT_END_CODE) > 0 
            THEN 'C'
            
            -- 그 외에는 등급 없음 (NULL)
            ELSE NULL 
        END AS GRADE,
        D.ID,
        D.EMAIL
    FROM 
        DEVELOPERS D,
        REQUIRED_SKILLS S -- CROSS JOIN (S 테이블은 항상 1행이므로 모든 D에 S의 컬럼이 붙음)
)
/*
  최종적으로 등급이 부여된(NULL이 아닌) 개발자만 선택하여
  GRADE와 ID 순으로 정렬합니다.
*/
SELECT 
    GRADE,
    ID,
    EMAIL
FROM 
    GRADED_DEVELOPERS
WHERE 
    GRADE IS NOT NULL
ORDER BY 
    GRADE ASC, 
    ID ASC;