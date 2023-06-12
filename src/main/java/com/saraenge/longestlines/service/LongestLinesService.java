package com.saraenge.longestlines.service;

import com.saraenge.longestlines.model.JourneyPatternPointOnLine;
import com.saraenge.longestlines.model.dto.LineInfoDTO;
import com.saraenge.longestlines.repository.JourneyPatternPointOnLineRepository;
import com.saraenge.longestlines.repository.StopPointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LongestLinesService {

    private final JourneyPatternPointOnLineRepository journeyRepository;
    private final StopPointRepository stopPointRepository;


    public LongestLinesService(
            final JourneyPatternPointOnLineRepository journeyRepository,
            final StopPointRepository stopPointRepository
    ) {
        this.journeyRepository = journeyRepository;
        this.stopPointRepository = stopPointRepository;
    }


    public Iterable<JourneyPatternPointOnLine> loadLinesStops() {
        return journeyRepository.findAll();
    }

    public List<LineInfoDTO> getTop10LongestLines() {
        List<LineInfoDTO> top15LinesWithMostStops = journeyRepository.findTop15LinesWithMostStops();
        return top15LinesWithMostStops;
    }

}
