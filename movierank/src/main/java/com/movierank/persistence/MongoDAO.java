package com.movierank.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.movierank.domain.MovieDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MongoDAO {
	
	// mongoOperations와 mongoTemplate차이
	// mongoOperations : sqlSession 같은 역할, 왠만한 기능은 혼자서 가능
	// mongoTemplate : 디테일이 떨어지지만 사용자가 기능을 일일이 설정해주면 디테일하게 사용 가능
	@Autowired
	private MongoOperations mongoOper;
	
	public void save(MovieDTO mDto) {
		log.info(">>>>> 영화 랭킹정보 MongoDB에 저장");
		// mongoOper.insert : _id에 중복값이 들어가면 에러발생
		// mongoOper.save : 중복값이 들어갈 경우 update해버림 (기존 값을 지우고 새로운 값 저장)
		mongoOper.save(mDto);
	}
	
	public void dropCol() {
		log.info(">>>>> Collection Drop");
		// 컬렉션을 통째로 삭제		
		mongoOper.dropCollection("movie");
	}
	
	public List<MovieDTO> movieList() {
		log.info(">>>>> 영화 랭킹정보 MongoDB에 저장");
		// Criteria : key값
		// Criteria cri = new Criteria(key);
		// >> 이 컬럼에서
		
		// Qeury : value값
		// cri.exists(value);
		// >> 이 값을 모두 찾아라
		
		// == SELECT * FROM movie
		//	  WHERE key = value;
		Criteria cri = new Criteria();
		Query query = new Query(cri);
		
		// mongoOper.find(query, MovieDTO.class, "movie");
		// MovieDTO.class = resultType
		// "movie" = 어떤 컬렉션에 넣을 것인지 명시
		List<MovieDTO> list = mongoOper.find(query, MovieDTO.class, "movie");
		for(MovieDTO one : list) {
			log.info(one.toString());
		}
		return list;
	}
}
