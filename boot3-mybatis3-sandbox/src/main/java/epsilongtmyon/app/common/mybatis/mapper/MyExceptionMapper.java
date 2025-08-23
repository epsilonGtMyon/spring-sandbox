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
			   #{exKey}
			  ,#{amount}
			  ,#{createdAt}
			  ,#{updatedAt}
			)
						""")
	int insert(MyException myException);
}
