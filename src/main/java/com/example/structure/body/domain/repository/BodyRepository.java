package com.example.structure.body.domain.repository;

import com.example.structure.body.domain.Body;
import com.example.structure.body.dto.request.BodyRequest;
import com.example.structure.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BodyRepository extends JpaRepository<Body,Long> {



    List<Body> findAllByMemberCodeMemberCode(final Integer memberCode);



}
