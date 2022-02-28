package kukim.tistory.com.hellospring.repository;

import kukim.tistory.com.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("kukim");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        assertThat(result).isEqualTo(member);
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("kukim");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("hwi");
        repository.save(member2);

        Member result = repository.findByName("hwi").get();

        assertThat(result).isEqualTo(member2);
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("kukim");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("hwi");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
