package hello.servlet.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member =
            Member.builder()
            .username("Chanbin")
            .age(30)
            .build();

        //when
        memberRepository.save(member);


        //then
        Member findMember = memberRepository.findById(member.getId());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        //given
        Member member1 = Member.builder().username("member1").age(30).build();
        Member member2 = Member.builder().username("member2").age(20).build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result).hasSize(2);
        assertThat(result).contains(member1, member2);
    }
}
