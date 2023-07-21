package com.jpastudy.board.repository;

import com.jpastudy.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardsRepository extends JpaRepository<Board, Long>{
    //pageable을 매개변수로 받도록 한다. 클라이언트로 부터 전달된 페이지 번호와 크기 가반으로 해당 페이지의 데이터를 가져 옴
    Page<Board> findAllByOrderByCreatedAtDesc(Pageable pageable);

    //Containing을 붙여주면 Like 검색이 가능하다. %keyword%
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}
