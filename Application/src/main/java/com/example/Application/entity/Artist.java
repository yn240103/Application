package com.example.Application.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "artists")
@NoArgsConstructor
public class Artist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer artistId;
	
	private String artistName;
	private String artistHiraganaName;
	private String artistArtUrl;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "artistId")
	private List<Member> members;
}
