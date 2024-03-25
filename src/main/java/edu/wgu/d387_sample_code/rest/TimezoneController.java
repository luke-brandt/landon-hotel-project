package edu.wgu.d387_sample_code.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RestController
@RequestMapping("/time")
@CrossOrigin
public class TimezoneController {


    LocalDateTime estTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 0)); // 1:00 PM EST
    final private String dateTimeFormat = "hh:mma";

    @GetMapping("/presentation")
    public ResponseEntity<List<String>> getPresentationTimes(){

        List<String> presentationTimes = new ArrayList<String>();

        try{


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);

            ZonedDateTime estDateTime = ZonedDateTime.of(estTime, ZoneId.of("America/New_York"));
            presentationTimes.add(estDateTime.format(formatter) + " ET");


            ZonedDateTime mstDateTime = estDateTime.withZoneSameInstant(ZoneId.of("America/Denver"));
            presentationTimes.add(mstDateTime.format(formatter) + " MT");

            ZonedDateTime utcDateTime = estDateTime.withZoneSameInstant(ZoneOffset.UTC);
            presentationTimes.add(utcDateTime.format(formatter.withZone(ZoneOffset.UTC)) + " UTC");




            System.out.println("EST: " + estDateTime.format(formatter));
            System.out.println("MST: " + mstDateTime.format(formatter));
            System.out.println("UTC: " + utcDateTime.format(formatter.withZone(ZoneOffset.UTC)));
//            ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
//
//            String estDateTime = zonedDateTime.format(formatter);
//
//            System.out.println("EST:     " + estDateTime);
//            presentationTimes.add(estDateTime);
//
//
//            zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Denver"));
//
//            String mstDateTime = zonedDateTime.format(formatter);
//
//            System.out.println("MST:     " + mstDateTime);
//            presentationTimes.add(mstDateTime);
//
//
//            zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
//
//            String utcDateTime = zonedDateTime.format(formatter);
//
//            System.out.println("UTC:     " + utcDateTime);
//            presentationTimes.add(utcDateTime);


        } catch (Exception e){
            System.out.println(e.toString());
        }

        return ResponseEntity.ok(presentationTimes);
    }


}
