package com.chainhaus.mtg.repository;

import com.chainhaus.mtg.entity.MenuLink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * Created by Asad Sarwar on 21/06/2020.
 */
public interface MenuLinkRepository extends CrudRepository<MenuLink, Long> {

    @Query("select ml from MenuLink ml where ml.routerLink in :routerLinks")
    Set<MenuLink> findByRouterLinks(@Param("routerLinks") String... routerLinks);

}
