package edu.wgu.d387_sample_code.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@RestController
@RequestMapping("/time")
@CrossOrigin
public class TimezoneController {

    final private String startTime = "01:00PM EST";
    final private String dateTimeFormat = "hh:mma z";
    final private String[] timeZones = new String[]{"EST", "MST", "UTC"};
    private SimpleDateFormat timeFormater = new SimpleDateFormat(dateTimeFormat);

    @GetMapping("/presentation")
    public ResponseEntity<List<String>> getPresentationTimes(){

        List<String> presentationTimes = new ArrayList<String>();

        try{
            Date date = timeFormater.parse(startTime);

            for(String time: timeZones){
                TimeZone tz = TimeZone.getTimeZone(time);
                timeFormater.setTimeZone(tz);
                String newDate = timeFormater.format(date);
                presentationTimes.add(newDate);
            }

        } catch (Exception e){
            System.out.println(e.toString());
        }

        return ResponseEntity.ok(presentationTimes);
    }


}
