package com.example.bcyimitation.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Paging {
    private int pageSize; //一页站多少个
    private int pageNumber; //当前第几页
    private int pageStart; //分多少页
    private long total;  //总共分几页
    private List<?> list; //存放的数据列

    private int id; //当前列表的id
    private List<?> tags;//某些分页中需要显示标签
//    private List<>


}
