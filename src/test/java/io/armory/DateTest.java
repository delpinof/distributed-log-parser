package io.armory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import io.armory.model.DistributedLogLine;

import static org.junit.Assert.assertEquals;

public class DateTest {

    String dateString = "2016-12-20T19:00:45Z";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Before
    public void setup() {

    }

    @Test
    public void createDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = sdf.parse(dateString);
        System.out.println(date);
    }

    @Test
    public void dateOrder() throws ParseException {
        Queue<DistributedLogLine> logs = new PriorityQueue<>();

        logs.offer(new DistributedLogLine("2016-12-20T18:00:45Z"));
        logs.offer(new DistributedLogLine("2016-12-20T17:00:45Z"));
        logs.offer(new DistributedLogLine("2016-12-20T19:00:45Z"));

        assertEquals(sdf.parse("2016-12-20T17:00:45Z"), logs.poll().getDate());
    }
}
