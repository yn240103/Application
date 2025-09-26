package com.example.Application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Application.entity.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {
    List<Member> findByArtistId(Integer artistId);
    List<Member> findByArtistIdAndMemberNameContaining(Integer artistId, String memberName);
}
