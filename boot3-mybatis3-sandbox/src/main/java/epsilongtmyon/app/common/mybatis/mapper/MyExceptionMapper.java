package epsilongtmyon.app.common.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import epsilongtmyon.app.common.mybatis.entity.MyException;

@Mapper
public interface MyExceptionMapper {

	@Insert("""
			insert into MY_EXCEPTION (
			   EX_KEY
			  ,AMOUNT
			  ,CREATED_AT
			  ,UPDATED_AT
			) values (
			   #{exKey, jdbcType=VARCHAR}
			  ,#{amount, jdbcType=NUMERIC}
			  ,#{createdAt, jdbcType=TIMESTAMP}
			  ,#{updatedAt, jdbcType=TIMESTAMP}
			)
						""")
	int insert(MyException myException);
}
