package com.onetool.spider.common;

/**
 * @author: zh
 * @date: 2023/3/23 10:15
 * @description: yt-dlp 操作命令行枚举
 */

public enum YtDlpEnum {

    //提取最高质量音频 格式 -f ba
    //将视频存为Mp3 -x --audio-format mp3
    //自定义文件名称-o '%（id）s.%（ext）s'
    AUDIO_FORMAT_MP3("yt-dlp -f 'ba' -x --audio-format mp3 {0} -o {1}.mp3");

    private String value;

    YtDlpEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
