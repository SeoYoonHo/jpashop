package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
class MemberRepositoryTest {
	
	@Autowired
	MemberRepository memberRepository;

	@Test
	@Transactional // test에 있는 이 어노테이션은 테스트 종료시 RollBack
	void testMember() {
		//given
		Member member = new Member();
		member.setUsername("memberA");
		
		//when
		Long saveId = memberRepository.save(member);
		Member findMember = memberRepository.find(saveId);
		
		//then
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		Assertions.assertThat(findMember).isEqualTo(member); // 같은 영속성 컨텍스트 안에서 식별자(ID)가 같으면 같은 객체이다. --> 성능향상
	}

}
