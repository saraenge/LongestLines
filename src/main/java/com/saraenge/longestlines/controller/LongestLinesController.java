package com.saraenge.longestlines.controller;

import com.saraenge.longestlines.model.dto.LineInfoDTO;
import com.saraenge.longestlines.model.dto.StopsInfoDTO;
import com.saraenge.longestlines.service.LongestLinesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/longestLines")
public class LongestLinesController {

    private final LongestLinesService longestLinesService;

    public LongestLinesController(LongestLinesService longestLinesService) {
        this.longestLinesService = longestLinesService;
    }

    @GetMapping("/lines")
    public List<LineInfoDTO> getTop10Lines() {
        return longestLinesService.getTop10LongestLines();
    }

    @GetMapping("/stops")
    public StopsInfoDTO getTop10Lines(@RequestParam int line, @RequestParam int direction) {
        return longestLinesService.getStopsByLine(line, direction);
    }
}
