package com.saraenge.longestlines.repository;

import com.saraenge.longestlines.model.JourneyPatternPointOnLine;
import com.saraenge.longestlines.model.dto.LineInfoDTO;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JourneyPatternPointOnLineRepository extends CrudRepository<JourneyPatternPointOnLine, Integer> {

    @Query("SELECT LINE_NUMBER , DIRECTION_CODE AS direction , COUNT(*) AS number_of_stops\n" +
            "FROM JOURNEY_PATTERN_POINT_ON_LINE \n" +
            "GROUP BY LINE_NUMBER , DIRECTION_CODE \n" +
            "ORDER BY number_of_stops DESC\n" +
            "LIMIT 15;")
    List<LineInfoDTO> findTop15LinesWithMostStops();
}
