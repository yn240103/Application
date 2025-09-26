package com.example.Application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Application.entity.Artist;
import com.example.Application.entity.Member;


@Repository
public interface ArtistRepository extends CrudRepository<Artist,Integer>{
	List<Member> findByArtistId(Integer artistId);
	public List<Artist> findAll();
	public List<Artist> findByArtistNameContaining(String artistName);
}
