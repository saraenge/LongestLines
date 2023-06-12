package com.saraenge.longestlines.repository;

import com.saraenge.longestlines.model.JourneyPatternPointOnLine;
import com.saraenge.longestlines.model.dto.LineInfoDTO;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JourneyPatternPointOnLineRepository extends CrudRepository<JourneyPatternPointOnLine, Integer> {

    @Query("SELECT LINE_NUMBER , DIRECTION_CODE AS direction , COUNT(*) AS number_of_stops " +
            "FROM JOURNEY_PATTERN_POINT_ON_LINE " +
            "GROUP BY LINE_NUMBER , DIRECTION_CODE " +
            "ORDER BY number_of_stops DESC " +
            "LIMIT 15;")
    List<LineInfoDTO> findTop15LinesWithMostStops();

    @Query("SELECT s.STOP_POINT_NAME FROM JOURNEY_PATTERN_POINT_ON_LINE j " +
            "JOIN STOP_POINT s " +
            "WHERE j.STOP_POINT_NUMBER = s.STOP_POINT_NUMBER " +
            "AND j.LINE_NUMBER = :line AND j.DIRECTION_CODE = :direction ")
    List<String> findStopsByLine(final int line, final int direction);
}
