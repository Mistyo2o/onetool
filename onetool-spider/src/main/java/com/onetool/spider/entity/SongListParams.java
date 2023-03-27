package com.onetool.spider.entity;

/**
 * @author: zh
 * @date: 2023/3/27 17:11
 * @description: 歌单歌曲列表请求参数
 */
public class SongListParams {

    private String req_1;

    public SongListParams() {
    }

    //根据歌单id 和 查询数量初始化请求参数
    public static SongListParams init(String dissid, int songNum){
        SongListParams params = new SongListParams();
        String req1 = "{\"module\":\"music.srfDissInfo.aiDissInfo\",\"method\":\"uniform_get_Dissinfo\",\"param\":{\"disstid\":"+dissid+",\"userinfo\":1,\"tag\":1,\"orderlist\":1,\"song_begin\":0,\"song_num\":"+songNum+",\"onlysonglist\":0,\"enc_host_uin\":\"\"}}";
        params.setReq_1(req1);
        return params;
    }

    public String getComm() {
        return "{\"cv\":4747474,\"ct\":24,\"format\":\"json\",\"inCharset\":\"utf-8\",\"outCharset\":\"utf-8\",\"notice\":0,\"platform\":\"yqq.json\",\"needNewCode\":1,\"uin\":2548635937,\"g_tk_new_20200303\":2019711931,\"g_tk\":2019711931}";
    }

    public String getReq_1() {
        return req_1;
    }

    public void setReq_1(String req_1) {
        this.req_1 = req_1;
    }

    public String getReq_2() {
        return "{\"module\":\"MessageCenter.MessageCenterServer\",\"method\":\"GetMessage\",\"param\":{\"uin\":\"2548635937\",\"red_dot\":[{\"msg_type\":1}]}}";
    }

    public String getReq_3() {
        return "{\"module\":\"GlobalComment.GlobalCommentMessageReadServer\",\"method\":\"GetMessage\",\"param\":{\"uin\":\"2548635937\",\"page_num\":0,\"page_size\":1,\"last_msg_id\":\"\",\"type\":0}}";
    }


}
