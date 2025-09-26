package com.example.Application.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Application.entity.Artist;
import com.example.Application.entity.Member;
import com.example.Application.service.ArtistService;
import com.example.Application.service.MemberService;

@Controller
public class ArtistController {
	
	private final ArtistService artistService;
	private final MemberService memberService;
	
	@Autowired
	public ArtistController(ArtistService artistService,MemberService memberService ) {
		this.artistService = artistService;
		this.memberService = memberService;
	}
	
	@GetMapping("/artists")
	public String getArtist(@RequestParam(value="keyword", required=false) String keyword, Model model) {
	    List<Artist> artists;
	    if (keyword != null && !keyword.isEmpty()) {
	        artists = artistService.findByArtistName(keyword);
	    } else {
	        artists = artistService.findAll();
	    }
	    model.addAttribute("artists", artists);
	    return "artists";
	}
	
	@DeleteMapping("/artists/{id}")
	public String deleteArtist(@PathVariable("id") Integer id) {
	    artistService.deleteArtist(id);
	    return "redirect:/artists";
	}
	
	@GetMapping("/artists/signup")
	public String newArtistPage(Model model) {
	    model.addAttribute("artist", new Artist());
	    return "signUpArtist";
	}
	
	@PostMapping("/artists/signup")
	public String saveArtist(@ModelAttribute Artist artist, @RequestParam("imageFile") MultipartFile imageFile) {
	    try {
	        artistService.saveArtist(artist, imageFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "redirect:/error";
	    }
	    return "redirect:/artists";
	}
	
	@GetMapping("/artists/{id}")
	public String getArtistDetails(Model model, @PathVariable("id") Integer id, @RequestParam(value="memberKeyword", required=false) String memberKeyword) {
		Artist artist = artistService.findById(id);
		List<Member> members;
		if (memberKeyword != null && !memberKeyword.isEmpty()) {
			members = memberService.findByArtistIdAndMemberName(id, memberKeyword);
		} else {
			members = memberService.findByArtistId(id);
		}
		
		model.addAttribute("artist", artist);
		model.addAttribute("members", members);
		
		return "artistDetails";
	}
	
	@GetMapping("/artists/update/{id}")
	public String editArtist(@PathVariable("id") Integer id, Model model) {
	    Artist artist = artistService.findById(id);
	    model.addAttribute("artist", artist);
	    return "signUpArtist";
	}
}
