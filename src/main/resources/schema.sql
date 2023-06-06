create table Todo(
    id int not null,
    user_id int not null,
    title varchar(100),
    completed boolean,
    version int
);

create table StopPoint(
    id int not null,
    stop_point_number int not null,
    stop_point_name varchar(100)
);

create table JourneyPatternPointOnLine(
    id int not null,
    lineNumber int not null,
    directionCode int not null,
    JourneyPatternPointNumber int not null
);