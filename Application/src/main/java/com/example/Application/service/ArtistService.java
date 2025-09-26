package com.example.Application.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Application.entity.Artist;
import com.example.Application.repository.ArtistRepository;

@Service
public class ArtistService {
	
	private final ArtistRepository artistRepository;
	private final Path imageStorageLocation = Paths.get("src/main/resources/static/images").toAbsolutePath().normalize();

	@Autowired
	public ArtistService(ArtistRepository artistRepository) {
		this.artistRepository = artistRepository;
	}
	
	public List<Artist> findAll(){
		return artistRepository.findAll();
	}
	
	public void deleteArtist(Integer id) {
        artistRepository.deleteById(id);
    }
	
	public Artist saveArtist(Artist artist, MultipartFile imageFile) throws IOException {
	    if (!imageFile.isEmpty()) {
	        String fileName = imageFile.getOriginalFilename();
	        Path targetLocation = this.imageStorageLocation.resolve(fileName);
	        Files.createDirectories(targetLocation.getParent());
	        Files.copy(imageFile.getInputStream(), targetLocation);
	        
	        artist.setArtistArtUrl("/images/" + fileName);
	    }
	    return artistRepository.save(artist);
	}

	public Artist findById(Integer id) {
	    return artistRepository.findById(id).orElse(null);
	}
	
	public List<Artist> findByArtistName(String keyword) {
		return artistRepository.findByArtistNameContaining(keyword);
	}
}
