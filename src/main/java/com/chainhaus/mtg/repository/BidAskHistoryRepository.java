package com.chainhaus.mtg.repository;

import com.chainhaus.mtg.entity.BidAskHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidAskHistoryRepository extends JpaRepository<BidAskHistory, Integer> {

    @Query(value = "SELECT b FROM BidAskHistory b where b.askPrice = (SELECT max(b.askPrice) FROM BidAskHistory b)")
    BidAskHistory getMaxAskHistory();

    List<BidAskHistory>  findAllByPid(Integer pid);

    @Query(value = "SELECT b FROM BidAskHistory b where b.bidPrice = (SELECT max(b.bidPrice) FROM BidAskHistory b) and b.pid = :pid")
    List<BidAskHistory> getMaxBidHistory(@Param("pid") Integer pid);

}
