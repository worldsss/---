package com.example.bcyimitation.pojo.qu;

import lombok.Data;

import java.util.List;

@Data
public class Qu_content {

    private Integer quid;
    private Integer uid;
    private String qu_text;
    private String qu_info;
    private List<Qu_imgs> qu_imgs;


}
