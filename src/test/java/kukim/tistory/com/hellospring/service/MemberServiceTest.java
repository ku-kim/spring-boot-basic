package kukim.tistory.com.hellospring.service;

import kukim.tistory.com.hellospring.domain.Member;
import kukim.tistory.com.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member1 = new Member();
        member1.setName("kukim");

        // when
        Long saveId = memberService.join(member1);
        
        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(findMember.getName()).isEqualTo(member1.getName());
    }

    @Test
    void 중복회원가입() {
        // given
        Member member1 = new Member();
        member1.setName("kukim");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("kukim");

        assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

//    @Test
//    void findMembers() {
//
//    }

    @Test
    void findOne() {
        Member member1 = new Member();
        member1.setName("kukim");
        memberService.join(member1);
        Member member2 = new Member();
        member2.setName("miller");
        memberService.join(member2);

        Member result = memberService.findOne(member1.getId()).get();

        assertThat(result.getId()).isEqualTo(member1.getId());

    }
}