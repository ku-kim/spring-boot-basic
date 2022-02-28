package kukim.tistory.com.hellospring;

import kukim.tistory.com.hellospring.repository.JdbcMemberRepository;
import kukim.tistory.com.hellospring.repository.JdbcTemplateMemberRepository;
import kukim.tistory.com.hellospring.repository.MemberRepository;
import kukim.tistory.com.hellospring.repository.MemoryMemberRepository;
import kukim.tistory.com.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }


}
