package com.dell.emc.repository;

import com.dell.emc.model.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrgRepository extends JpaRepository<Org, Long> {}
