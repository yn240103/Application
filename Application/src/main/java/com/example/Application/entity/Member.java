package com.example.Application.entity;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "members")
@NoArgsConstructor
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberId;
	private Integer artistId;
	private String memberName;
	private String memberHiraganaName;
	private LocalDate memberBirthday;
	
	@Lob
	@Column(name = "member_image", columnDefinition="BLOB")
	private byte[] memberImage;
	
	public int getAge() {
        return Period.between(memberBirthday, LocalDate.now()).getYears();
    }
}
