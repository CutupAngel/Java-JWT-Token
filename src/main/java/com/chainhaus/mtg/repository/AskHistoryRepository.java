package com.chainhaus.mtg.repository;

import com.chainhaus.mtg.entity.AskHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AskHistoryRepository extends JpaRepository<AskHistory, Integer> {

    @Query(value = "SELECT b FROM AskHistory b where b.createdAt = (SELECT max(b.createdAt) FROM AskHistory b)")
    AskHistory getMaxAskHistory();

    List<AskHistory>  findAllByPid(Integer pid);

}
