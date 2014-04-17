-- assign3.sql by Sterling Kohel --


--Query for problem 1--
SELECT pname AS Project, min(hours) AS Minimum, max(hours) AS Maximum, SUM(hours) AS Total
FROM Proj, WorksOn
WHERE Proj.pno = WorksOn.pno
GROUP BY pname;

--Query for problem 2--
SELECT Distinct pname AS Project, m.ename AS Manager, e.ename AS Employee
FROM Emp m, Emp e, WorksOn w1, WorksOn w2, Proj
WHERE 	m.eno = w1.eno 
AND 	e.eno = w2.Eno 
AND 	m.eno <> e.eno 
AND 	w1.pno = w2.pno 
AND 	w1.pno = Proj.pno 
AND 	w1.resp = 'Manager' 
AND 	w2.hours > w1.hours;

--Query for problem 3--
SELECT ename AS Name
FROM Emp, Dept
WHERE Emp.eno = Dept.mgreno 
UNION 	SELECT a.ename 
		FROM emp a, emp b 
		WHERE a.eno = b.supereno
EXCEPT 	SELECT DISTINCT a.ename
		FROM Emp a, Emp b, Dept
		WHERE a.eno = b.supereno 
		AND a.eno = Dept.mgreno;
		
--Query for problem 4--
SELECT pname as Project
FROM Emp, Dept, WorksOn, Proj
WHERE 	Emp.eno = mgreno 
AND 	Emp.eno = WorksOn.eno 
AND 	WorksOn.pno = Proj.pno 
AND 	Proj.dno = Dept.dno
UNION 	SELECT pname
		FROM Proj, WorksOn
		WHERE WorksOn.pno = Proj.pno 
		GROUP BY pname
		having count(eno) > 5; 

--Query for problem 5--
SELECT AVG(salary) AS Avg_Salary, dname AS Department 
FROM Emp, Dept
WHERE 	bdate > date('1970-12-01')
AND 	Emp.dno = Dept.dno
GROUP BY Emp.dno
HAVING 	AVG(salary) > 50000;

--Query for problem 6--
SELECT	dname AS Department	
FROM Emp, Dept, WorksOn, Proj
WHERE	Emp.eno = WorksOn.eno
AND		WorksOn.pno = Proj.pno
AND 	Proj.dno = Dept.dno
AND 	title = 'ME'
GROUP BY Dept.dno
HAVING count(WorksOn.eno) >= 2;

--Query for problem 7--
SELECT ename AS Name, sum(hours) AS Total_Hours
FROM Emp, WorksOn
WHERE Emp.eno = WorksOn.eno
GROUP BY emp.eno;

--Query for problem 8--
SELECT Distinct e1.ename as Name
FROM Emp e1, Emp e2
WHERE e1.ename = e2.ename
AND e1.eno <> e2.eno;