package edu.bit.ex.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmpVO {
   
   private String empno;
   private String ename;
   private String job;
   private Timestamp hiredate;
   private int sal;
   private int comm;
   private int deptno;
   
   // 괄호는 두줄이상일경우에는 무조건 써줘야하지만 하줄일경우에는 생략이 가능하다
   public String getAuthority() {
      if(getJob().toUpperCase().contains("MANAGER")) {
         return "ROLE_ADMIN";
      }
       else
         return "ROLE_USER";
      
   }

}