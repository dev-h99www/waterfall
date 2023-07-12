package com.greedy.waterfall.project.model.dto;

import com.greedy.waterfall.board.model.dto.MeetingMemberDTO;
import com.greedy.waterfall.menu.model.dto.ProjectInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : ProjectDTO
 * Comment : 프로젝트의 정보를 저장한다.
 * 
 * History
 * 2022. 2. 25.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ProjectDTO {

   private int no;                  //프젝번호
   private String name;            //프젝명
   private java.sql.Date startDate;   //시작일
   private java.sql.Date deadLine;    //마감일
   private int progression;         //진행율
   private String status;           //프로젝트상태여부 
   private java.sql.Date completed;   //종료일
   private String statusCode;          //상태코드
   
   private MeetingMemberDTO member;
   
   private ProjectInfoDTO projectInfo;

}