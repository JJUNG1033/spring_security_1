package edu.bit.ex.service;

import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bit.ex.mapper.UserMapper;
import edu.bit.ex.vo.UserVO;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@NoArgsConstructor
public class TxService {

	@Inject
	private UserMapper userMapper;

	// 정상동작
	public void txTest1() {

		log.info("transionTest1()..테스트");

		// 순서가 바뀌면 안된다

		userMapper.deleteAuthorities();
		userMapper.deleteUsers();

		UserVO user = new UserVO();
		user.setUsername("abcd");
		user.setPassword("1111");

		userMapper.insertUser(user);

		user.setUsername("efg");
		user.setPassword("2222");

		userMapper.insertUser(user);

	}

	public void txTest2() {
		// 롤백시키고싶다는 의미이다
		log.info("transionTest2()..테스트");

		// 순서가 바뀌면 안된다
		userMapper.deleteAuthorities();
		userMapper.deleteUsers();

		UserVO user = new UserVO();
		user.setUsername("abcd");
		user.setPassword("1111");

		userMapper.insertUser(user);

		user.setUsername("efg");
		user.setPassword("2222");

		// 일부러 에러를 냄
		userMapper.insertUser(null);

	}

	@Transactional
	public void txTest3() {
		// 롤백시키고싶다는 의미이다
		log.info("transionTest3()..테스트");

		// 순서가 바뀌면 안된다
		userMapper.deleteAuthorities();
		userMapper.deleteUsers();

		UserVO user = new UserVO();
		user.setUsername("abcd");
		user.setPassword("1111");

		userMapper.insertUser(user);

		user.setUsername("efg");
		user.setPassword("2222");

		userMapper.insertUser(null);

	}

	// uncheckedExeption(롤백 함)
	@Transactional
	public void txTest4() {
		// 이전상태로 돌린다고하더라도 에러가 떠야한다
		// 그리고 정보가 sql에서 사라지지 않는다
		userMapper.deleteAuthorities();
		userMapper.deleteUsers();

		throw new RuntimeException("RuntimeException for rollback");
	}
//	   //CheckedExeption 테스트(롤백 안함)
//	   @Transactional
//	   public void txTest5() throws SQLException {
//	      try {
//	      userMapper.deleteAuthorities();
//	      userMapper.deleteUsers();
//
//	      throw new SQLException("SQLException for rollback");
//	   }catch(Exception e) {
//		   // 개발자가 기본적으로 에러처리에 대한 코딩을 한다는 의미이다
//		   // 고슬링아저씨가 이렇게만듬 왜?
//	   }

//CheckedExeption 테스트(롤백 안함)
	@Transactional
	public void txTest5() throws SQLException {

		userMapper.deleteAuthorities();
		userMapper.deleteUsers();

		throw new SQLException("SQLException for rollback");
	}

	@Transactional(rollbackFor = SQLException.class)
	public void txTest6() throws SQLException {

		userMapper.deleteAuthorities();
		userMapper.deleteUsers();

		throw new SQLException("SQLException for rollback");
	}

	// @Transactional의 rollbackFor 옵션을 이용하면 Rollback이 되는 클래스를 지정가능함.
	// Exception예외로 롤백을 하려면 다음과 같이 지정하면됩니다. @Transactional(rollbackFor =
	// Exception.class)
	// 여러개의 예외를 지정할 수도 있습니다. @Transactional(rollbackFro = {RuntimeException.class,
	// Exception.class})
	// POLYMORPHSISM이 적용되는것이다

	@Transactional(rollbackFor = Exception.class)
	public void txTest7() throws SQLException {
		userMapper.deleteAuthorities();
		userMapper.deleteUsers();

		throw new SQLException("SQLException for rollback");
	}

}
