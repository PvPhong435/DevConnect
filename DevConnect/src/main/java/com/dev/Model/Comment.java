package com.dev.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @Column(name = "commentid")
    private String id;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createat = new Date();
    @ManyToOne
    @JoinColumn(name = "articleid")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public String createAtAsString() {
        return formatTimeAgo(createat.getTime());
    }

    private String formatTimeAgo(long duration) {
        Duration duration1 = Duration.between(Instant.ofEpochMilli(duration), Instant.now());
        Period period = Period.between(LocalDate.ofInstant(Instant.ofEpochMilli(duration), ZoneId.systemDefault()), LocalDate.now());
        if(period.getYears() > 1) {
            return period.getYears() + " năm trước";
        } else if (period.getMonths() > 1) {
            return period.getMonths() + " tháng trước";
        } else if (period.getDays() > 1) {
            return period.getDays() + " ngày trước";
        } else if (duration1.toHoursPart() > 1) {
            return duration1.toHoursPart() + " tiếng trước";
        } else if (duration1.toMinutesPart() > 1) {
            return duration1.toMinutesPart() + " phút trước";
        } else if (duration1.toSecondsPart() > 1) {
            return duration1.toSecondsPart() + " giây trước";
        } else
            return "0 giây trước";
    }
}
