package com.onetool.spider.controller;

import com.onetool.spider.entity.Song;
import com.onetool.spider.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;


@RequestMapping("/gen")
@RestController
public class EchoController {

    @Autowired
    private AudioService audioService;


    @RequestMapping("/v1")
    public String get() throws Exception {
        List<Song> keywords = new ArrayList<>();
        Song song = new Song();
        song.setName("晴天");
        song.setAuthor("周杰伦");
        keywords.add(song);
        audioService.youTubeVideo(keywords);
        return "OK";
    }

}
