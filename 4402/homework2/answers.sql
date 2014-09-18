-- Taylor Lapeyre
-- CSC 4402
-- Homework 2


-- Prep
CREATE TABLE Patient (
  pnum varchar(255),
  pname varchar(255),
  city varchar(255),
  street varchar(255),
  age smallint,
  primary_dnum varchar(255),
  PRIMARY KEY(pnum),
  FOREIGN KEY(primary_dnum) REFERENCES Doctor(dnum)
);

CREATE TABLE Doctor (
  dnum varchar(255),
  dname varchar(255),
  city varchar(255),
  street varchar(255),
  specialty varchar(255),
  PRIMARY KEY(dnum)
);

CREATE TABLE Visit (
  pnum varchar(255),
  dnum varchar(255),
  num_visits int(11),
  bill_balance int(11)
  FOREIGN KEY(pnum) REFERENCES Patient(pnum),
  FOREIGN KEY(dnum) REFERENCES Doctor(pnum)
);

-- Number 1
SELECT Doctor.dnum,Doctor.dname
FROM Doctor,Visit,Patient
WHERE Visit.dnum = Doctor.dnum
AND Visit.pnum = Patient.pnum
AND Doctor.specialty = "Pulmonology"
AND Patient.city = "Baton Rouge"
AND Visit.num_visits >= 4;

-- Number 2
SELECT
  Patient.pnum,
  Patient.pname,
  COUNT(DISTINCT Visit.dnum),
  SUM(Visit.bill_balance)
FROM Patient,Visit,Doctor
WHERE Patient.age >= 75
AND Patient.city = "Baton Rouge"
ORDER BY Visit.bill_balance DESC;


-- Number 3
SELECT
  Patient.pnum,
  Doctor.dnum
FROM Doctor,Patient,Visit
WHERE
  Visit.pnum = Patient.pnum AND
  Visit.dnum = Doctor.dnum AND
  Doctor.city = Patient.city AND
  Doctor.street != Doctor.street;

-- Number 4
SELECT
  Patient.pnum
FROM
  Patient,Doctor,Visit
WHERE Patient.city = "Lafayette"
OR (SELECT Patient.pnum
    FROM Patient,Doctor,Visit
    WHERE
      Patient.pnum = Visit.pnum AND
      Doctor.dnum = Visit.dnum AND
      Doctor.city = "Baton Rouge" AND
      Visit.bill_balance >= 500);

-- Number 5
SELECT
  Patient.pnum,
  Patient.pname
FROM Doctor,Patient,Visit
WHERE
  Patient.pnum = Visit.pnum AND
  Doctor.dnum = Visit.dnum AND
  Patient.city = Doctor.city
GROUP BY Patient.pnum
HAVING COUNT(DISTINCT Visit.dnum) >= 4;


-- Problem 6
SELECT
  Patient.pnum,
  Patient.pname,
  Patient.city
FROM Patient,Doctor
WHERE
  Patient.primary_dnum = Doctor.dnum AND
  Doctor.specialty = "Family Practice";

-- Problem 7
SELECT
  Doctor.dnum,
  Doctor.dname
FROM Doctor
WHERE NOT EXISTS
  (SELECT Patient.pnum,Doctor.dnum
   FROM Doctor,Patient,Visit
   WHERE
     Visit.pnum = Patient.pnum AND
     Visit.dnum = Doctor.dnum AND
     Patient.city = "Baton Rouge")

