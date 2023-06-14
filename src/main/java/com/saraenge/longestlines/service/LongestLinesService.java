package com.saraenge.longestlines.service;

import com.saraenge.longestlines.model.dto.LineInfoDTO;
import com.saraenge.longestlines.model.dto.StopsInfoDTO;
import com.saraenge.longestlines.repository.JourneyPatternPointOnLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LongestLinesService {

    private final JourneyPatternPointOnLineRepository journeyRepository;


    public LongestLinesService(final JourneyPatternPointOnLineRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    public List<LineInfoDTO> getTop10LongestLines() {
        return journeyRepository.findTop10LinesWithMostStops();
    }

    public StopsInfoDTO getStopsByLine(final int line, final int direction) {
        List<String> stopsByLine = journeyRepository.findStopsByLine(line, direction);
        return new StopsInfoDTO(line, stopsByLine);
    }

}
