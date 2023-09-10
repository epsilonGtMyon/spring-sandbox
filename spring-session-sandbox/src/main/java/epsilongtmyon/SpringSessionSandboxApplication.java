package epsilongtmyon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

/*
 * @EnableSpringHttpSessionをつける
 * 今回はjdbcを使ってDBへの永続化を検証
 * JdbcSessionPropertiesに設定値はある
 * 初期化のタイミングはモードは選べるみたい
 * 初期化のスクリプト(DDL)はH2だと spring-session-jdbcのなかに schema-h2.sqlがある 
 * 
 */
@SpringBootApplication
@EnableSpringHttpSession
public class SpringSessionSandboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSessionSandboxApplication.class, args);
	}
}
