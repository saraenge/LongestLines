package com.saraenge.longestlines.controller;

import com.saraenge.longestlines.model.JourneyPatternPointOnLine;
import com.saraenge.longestlines.model.StopPoint;
import com.saraenge.longestlines.model.dto.LineInfoDTO;
import com.saraenge.longestlines.repository.JourneyPatternPointOnLineRepository;
import com.saraenge.longestlines.repository.StopPointRepository;
import com.saraenge.longestlines.service.LongestLinesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/longestLines")
public class LongestLinesController {

    private final JourneyPatternPointOnLineRepository journeyRepository;
    private final StopPointRepository stopPointRepository;

    private final LongestLinesService longestLinesService;

    public LongestLinesController(final JourneyPatternPointOnLineRepository journeyRepository, final StopPointRepository stopPointRepository, LongestLinesService longestLinesService) {
        this.journeyRepository = journeyRepository;
        this.stopPointRepository = stopPointRepository;
        this.longestLinesService = longestLinesService;
    }

    public Iterable<JourneyPatternPointOnLine> loadLinesStops() {
        return journeyRepository.findAll();
    }

    public Iterable<StopPoint> loadAllStops() {
        return stopPointRepository.findAll();
    }

    public StopPoint findStopPointById(final int id) {
        return stopPointRepository.findById(id).orElse(null);
    }

    private void findTopTenLines() {
        Iterable<JourneyPatternPointOnLine> pointsOnLines = loadLinesStops();
        HashMap<Integer, List<Integer>> linesAndStops = new HashMap<>();
        pointsOnLines.forEach(linePoint ->  {
            int hashedLineId = linePoint.getHashedLineId();
            if (linesAndStops.containsKey(hashedLineId)) {
                linesAndStops.get(hashedLineId).add(linePoint.stopPoint());
            } else {
                linesAndStops.put(hashedLineId, new ArrayList<>(linePoint.stopPoint()));
            }
        });


    }

    @GetMapping("/lines")
    public List<LineInfoDTO> getTop10Lines() {
        return longestLinesService.getTop10LongestLines();
    }
}
