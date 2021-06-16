package edu.bit.ex.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import edu.bit.ex.vo.EmpVO;
import edu.bit.ex.vo.MemberVO;
import edu.bit.ex.vo.UserVO;

@Mapper
public interface EmpMapper {	
	
	@Select("select * from emp where ename = #{ename}")
	public EmpVO getEmp(String username);	
}
