package com.example.Application.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class MemberController {

    private final MemberService memberService;
    private final ArtistService artistService;

    @Autowired
    public MemberController(MemberService memberService, ArtistService artistService) {
        this.memberService = memberService;
        this.artistService = artistService;
    }

    @GetMapping("/members/signup")
    public String showMemberForm(@RequestParam(value = "artistId", required = false) Integer artistId, Model model) {
        model.addAttribute("member", new Member());
        List<Artist> artists = artistService.findAll();
        model.addAttribute("artists", artists);
        model.addAttribute("selectedArtistId", artistId);
        return "signUpMember";
    }

    @PostMapping("/members/signup")
    public String saveMember(@ModelAttribute Member member, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            if (!imageFile.isEmpty()) {
                member.setMemberImage(imageFile.getBytes());
            }
            memberService.saveMember(member);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/artists/" + member.getArtistId();
    }
    
    @GetMapping("/members/update/{id}")
    public String showUpdateMemberForm(@PathVariable("id") Integer id, Model model, @RequestParam(value = "artistId", required = false) Integer artistId) {
    	Member member = memberService.findById(id);
    	List<Artist> artists = artistService.findAll();
    	model.addAttribute("member", member);
    	model.addAttribute("artists", artists);
    	model.addAttribute("selectedArtistId", artistId);
    	return "signUpMember";
    }
}
