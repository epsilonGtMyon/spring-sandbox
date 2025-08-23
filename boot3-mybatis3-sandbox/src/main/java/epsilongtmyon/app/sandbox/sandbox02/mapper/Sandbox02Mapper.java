package epsilongtmyon.app.sandbox.sandbox02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import epsilongtmyon.app.common.mybatis.entity.MyTable;

// インターフェース名とは異なる場所にxmlファイルを配置している。
@Mapper
public interface Sandbox02Mapper {

	List<MyTable> findAll();
}
