package com.onetool.spider.controller;

import com.onetool.spider.dao.SongRepository;
import com.onetool.spider.entity.Song;
import com.onetool.spider.service.AudioService;
import com.onetool.spider.service.MinIoFileService;
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
    @Autowired
    private MinIoFileService minIoFileService;
    @Autowired
    private SongRepository songRepository;


    @RequestMapping("/v1")
    public String get() throws Exception {
//        List<Song> keywords = new ArrayList<>();
//        Song song = new Song();
//        song.setName("晴天");
//        song.setAuthor("周杰伦");
//        keywords.add(song);
//        audioService.youTubeVideo(keywords);
        List<Song> byPlayId = songRepository.findByPlayId("8121073639");
        audioService.youTubeVideo(byPlayId);
        return "OK";
    }

}
