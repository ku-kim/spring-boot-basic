package kukim.tistory.com.hellospring;

import kukim.tistory.com.hellospring.repository.MemberRepository;
import kukim.tistory.com.hellospring.repository.MemoryMemberRepository;
import kukim.tistory.com.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
