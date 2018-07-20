package mfw.index.client.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 电影
 */

@Getter
@Setter
public class SMovie {

    public SMovie(){
      
    }
//    public SMovie(Movie movie){
//      this.id = movie.getId();
//      this.mtimeId = movie.getMtimeId();
//      this.nameCN = movie.getNameCN();
//      this.nameEN = movie.getNameEN();
//      this.releaseDate = movie.getReleaseDate();
//      this.year = movie.getYear();
//      this.status = movie.getStatus().value();
//    }
    private int id;
    private int mtimeId;
    private String nameCN;
    private String nameEN;
    private LocalDate releaseDate;      // 公映日期

    private String year;    // 年代
    private Integer status; // 状态
    
    private LocalDateTime updateTime;
    private int updateUserId;
}
