create table Todo(
    id int not null,
    user_id int not null,
    title varchar(100),
    completed boolean,
    version int
);

create table STOP_POINT(
    id int not null,
    STOP_POINT_NUMBER int not null,
    STOP_POINT_NAME varchar(100),
    version int
);

create table JourneyPatternPointOnLine(
    id int not null,
    lineNumber int not null,
    directionCode int not null,
    JourneyPatternPointNumber int not null
);