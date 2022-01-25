DROP TABLE IF EXISTS SignalmenTBL;
DROP TABLE IF EXISTS PreferencesTBL;
DROP TABLE IF EXISTS individualDaysTBL;
DROP TABLE IF EXISTS HolidaysTBL;
DROP TABLE IF EXISTS shiftTBL;
DROP TABLE IF EXISTS DayDetailsTBL;

DROP VIEW IF EXISTS shiftView;


CREATE TABLE PreferencesTBL
(
    ID        INTEGER PRIMARY KEY,
    NameBreak INT
);

INSERT INTO PreferencesTBL (ID)
VALUES (1);

CREATE TABLE DayDetailsTBL
(
    day       INTEGER PRIMARY KEY,
    nameofday VARCHAR(15)
);
INSERT INTO DayDetailsTBL (day, nameofday)
VALUES (0, 'Sunday');
INSERT INTO DayDetailsTBL (day, nameofday)
VALUES (1, 'Monday');
INSERT INTO DayDetailsTBL (day, nameofday)
VALUES (2, 'Tuesday');
INSERT INTO DayDetailsTBL (day, nameofday)
VALUES (3, 'Wednesday');
INSERT INTO DayDetailsTBL (day, nameofday)
VALUES (4, 'Thursday');
INSERT INTO DayDetailsTBL (day, nameofday)
VALUES (5, 'Friday');
INSERT INTO DayDetailsTBL (day, nameofday)
VALUES (6, 'Saturday');

CREATE TABLE individualDaysTBL
(
    ID         INTEGER PRIMARY KEY,
    dayID      INTEGER,
    Weeknumber INT,
    FOREIGN KEY (dayID) REFERENCES DayDetailsTBL (day)
);
INSERT INTO individualDaysTBL (ID, dayID, Weeknumber)
VALUES (1, 4, 1),
       (2, 5, 1);

CREATE TABLE HolidaysTBL
(
    ID                 INTEGER PRIMARY KEY,
    DaysOfHolidaysUsed INT,
    Lengthofholiday    INT,
    DateOfHoliday      DATE

);

INSERT INTO HolidaysTBL (ID)
VALUES (1);

CREATE TABLE shiftTBL
(
    ID          INTEGER PRIMARY KEY,
    typeOfShift VARCHAR(15),
    shiftStart  INT,
    signalmanID INTEGER,
    dayID INTEGER,
    shiftEnd    INT,
    FOREIGN KEY (dayID) REFERENCES individualDaysTBL (ID),
    FOREIGN KEY (signalmanID) REFERENCES SignalmenTBL (ID)
);

INSERT INTO shiftTBL (ID, typeOfShift, shiftStart, shiftEnd, signalmanID, dayID)
VALUES (1, 'Day', 7, 19,1,1),
       (2, 'Night', 19, 7,2,2);

CREATE TABLE SignalmenTBL
(
    ID           INTEGER PRIMARY KEY,
    Firstname    VARCHAR(30),
    Lastname     VARCHAR(30),
    Email        VARCHAR(255),
    mobilenumber VARCHAR(15),
    FOREIGN KEY (ID) REFERENCES HolidaysTBL (ID)
);
INSERT INTO SignalmenTBL (Firstname, Lastname, Email, mobilenumber)
VALUES ('Ronnie', 'Braines', 'ronnie.braines@hotmail.com', '07498719043'),
       ('Anton', 'Leach', 'antonleach@bedmod.co.uk', '07732450039');

CREATE VIEW shiftView AS SELECT * FROM
shiftTBL JOIN SignalmenTBL ST on shiftTBL.signalmanID = ST.ID
JOIN individualDaysTBL iDT on iDT.ID = shiftTBL.dayID
JOIN DayDetailsTBL DDT on iDT.dayID = DDT.day;
--each shift has a signalman and a dayID created from the individualddaysTBL which forms a table with each shift

SELECT *
FROM PreferencesTBL;
SELECT *
FROM SignalmenTBL;
SELECT *
FROM shiftTBL;
SELECT *
FROM shiftView;