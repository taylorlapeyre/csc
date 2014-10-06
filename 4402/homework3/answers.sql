Taylor Lapeyre
Homework 4
CSC 4402

-- Section I
-- =================================================

-- Problem 1
SELECT DISTINCT EM.id, EM.name FROM EM
JOIN CR ON (CR.teacher_id  = EM.id)
JOIN PR ON (PR.director_id = EM.id);

-- Problem 2
SELECT id, AVG(grade) as average, COUNT(cnum) AS num_courses
FROM ER
NATURAL JOIN EM
WHERE city = "Baton Rouge"
GROUP BY name;

-- Problem 3
SELECT DISTINCT dept FROM EM
WHERE id IN
  (SELECT id
   FROM EP
   GROUP BY id
   HAVING COUNT(id) >= 3);

-- Problem 4
SELECT dept, budget, (
  SELECT SUM(salary)
  FROM EM
  WHERE EM.dept = CM.dept
) AS summed_salary, (
  SELECT COUNT(dept)
  FROM PR
  WHERE PR.dept = CM.dept
) AS num_projects
FROM CM
ORDER BY budget DESC;

-- Problem 5
SELECT EM.id,EM.name
FROM EM,EP,PR
WHERE
  EP.pnum = PR.pnum AND
  EP.id = EM.id AND
  PR.city = "Houston" AND
  EP.hour > 20 AND
  EM.dept != PR.dept;

-- Problem 6
SELECT pnum
FROM EP,EM,CM
HAVING COUNT(DISTINCT CM.dept) = COUNT(DISTINCT CASE
  WHEN EP.ID = EM.ID
  THEN EM.DEPT
END);

-- Problem 7
SELECT dept,budget, (
  SELECT COUNT(DISTINCT EM.id)
  FROM EM
  WHERE EM.dept = CM.dept
) AS num_in_department
FROM CM
WHERE budget > 250000
HAVING num_in_department > 5;


-- SECTION II
-- ======================================

-- E)
SELECT COUNT(id) AS total, sec_id, course_id
FROM Section
NATURAL LEFT JOIN Takes
WHERE
  semester = "Autumn" AND
  year = 2009
GROUP BY sec_id,course_id;

-- F)
SELECT MAX(enrolled_number)
FROM (
  SELECT COUNT(id) as enrolled_number
  FROM Section
  NATURAL LEFT JOIN Takes
  WHERE
    semester = "Autumn" AND
    year = 2009
    GROUP BY sec_id,course_id
)
