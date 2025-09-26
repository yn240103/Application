package com.example.Application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Application.entity.Member;
import com.example.Application.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findByArtistId(Integer artistId) {
        return memberRepository.findByArtistId(artistId);
    }
    
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
    
    public Member findById(Integer id) {
        return memberRepository.findById(id).orElse(null);
    }
    
    public List<Member> findByArtistIdAndMemberName(Integer artistId, String memberName) {
        return memberRepository.findByArtistIdAndMemberNameContaining(artistId, memberName);
    }
}
