package org.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;


/**
 * Created by Anton on 2016-05-05.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "ScheduleStamps")
public class ScheduleStamp {

    private long from;
    private long to;
    private String[] userId;

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ScheduleStamp() {

    }

    public ScheduleStamp(long from, long to, String[] userIds){
        this.from = from;
        this.to = to;
        this.userId = userIds;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public String[] getUserId() {
        return userId;
    }

    public void setUserId(String[] userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ScheduleStamp{" +
                "from=" + from +
                ", to=" + to +
                ", userId=" + Arrays.toString(userId) +
                ", id='" + id + '\'' +
                '}';
    }
}
