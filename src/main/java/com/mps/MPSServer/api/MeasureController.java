package com.mps.MPSServer.api;

import com.mps.MPSServer.CustomExceptions.ObjectNotFoundInDBException;
import com.mps.MPSServer.MPSUtil.DatetimeManager;
import com.mps.MPSServer.service.MeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "mps/api/measurement")
@RequiredArgsConstructor
public class MeasureController {

    private final MeasureService measureService;

    @GetMapping("v1/insert")
    public ResponseEntity<String> insertMeasurement(@RequestParam String panelName,
                                                    @RequestParam String channelName,
                                                    @RequestParam float value) {
        try {
            measureService.insertMeasurement(panelName, channelName, value);
            return ResponseEntity.ok("Measurement inserted");
        } catch (ObjectNotFoundInDBException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("v2/insert")
    public ResponseEntity<String> insertMeasurement(@RequestParam String panelName,
                                                    @RequestParam String channelName,
                                                    @RequestParam float value,
                                                    @RequestParam String ts) {
        try {
            measureService.insertMeasurement(panelName, channelName, value, ts);
            return ResponseEntity.ok("Measurement inserted");
        } catch (ObjectNotFoundInDBException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("v1/get-datetime")
    public ResponseEntity<String> get_current_datetime() {
        String dateISO = DatetimeManager.get_datetime_iso();
        dateISO = dateISO.replace(" ", "_");
        return ResponseEntity.ok(dateISO);
    }
}
