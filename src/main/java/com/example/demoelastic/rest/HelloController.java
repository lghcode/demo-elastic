package com.example.demoelastic.rest;

import com.example.demoelastic.model.Stu;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final ElasticsearchRestTemplate esTemplate;

    @RequestMapping("hello")
    public Object hello() {
        return "OK";
    }

    /**
     * 创建索引
     */
    @RequestMapping("createIndex")
    public Object createIndex() {
        esTemplate.indexOps(Stu.class).create();
        return "OK";
    }

    /**
     * 删除索引
     */
    @RequestMapping("deleteIndex")
    public Object deleteIndex() {
        esTemplate.indexOps(Stu.class).delete();
        return "OK";
    }

    /**
     * 判断索引是否存在
     */
    @RequestMapping("existIndex")
    public Object existIndex() {
        return esTemplate.indexOps(Stu.class).exists();
    }

    /**
     * 新增文档数据
     */
    @RequestMapping("addDoc")
    public Object addDoc() {

        Stu stu0 = new Stu(10010L, "绝世风华", 18, 100.5f, true);
        esTemplate.save(stu0);

        Stu stu1 = new Stu(10011L, "陌上千寻雪", 20, 88.5f, true);
        Stu stu2 = new Stu(10012L, "寒夜", 22, 108.5f, false);

        ArrayList<Stu> stuList = new ArrayList<>();
        stuList.add(stu1);
        stuList.add(stu2);
        esTemplate.save(stuList);

        return "OK";
    }

    /**
     * 根据文档id删除数据
     */
    @RequestMapping("deleteDoc")
    public Object deleteDoc(String docId) {
        esTemplate.delete(docId, Stu.class);
        return "OK";
    }

    /**
     * 查询文档数据
     */
    @RequestMapping("getDoc")
    public Object getDoc(String docId) {
        return esTemplate.get(docId, Stu.class);
    }

    /**
     * 修改文档数据
     */
    @RequestMapping("updateDoc")
    public Object updateDoc() {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "abc");
        map.put("money", 55.66f);

        Document doc = Document.from(map);

        UpdateQuery updateQuery = UpdateQuery
                .builder("10012")
                .withDocument(doc)
                .build();

        IndexCoordinates indexCoordinates = IndexCoordinates.of("stu");
        esTemplate.update(updateQuery, indexCoordinates);
        return "OK";
    }


    /**
     * 初始化数据
     */
    @RequestMapping("init")
    public Object init() {

        esTemplate.indexOps(Stu.class).delete();
        esTemplate.indexOps(Stu.class).create();

        Stu stu0 = new Stu(10010L, "诸天万界之起源传说", 18, 100.5f, true);
        Stu stu1 = new Stu(10011L, "寒夜", 20, 88.5f, true);
        Stu stu2 = new Stu(10012L, "陌上千寻雪", 22, 96.5f, false);
        Stu stu3 = new Stu(10013L, "可爱的漂亮的小哥哥", 26, 108.5f, false);
        Stu stu4 = new Stu(10014L, "灵纪传说", 28, 108.6f, true);
        Stu stu5 = new Stu(10015L, "狂剑天下之鸿蒙掌控", 16, 18.5f, false);
        Stu stu6 = new Stu(10016L, "逆战次元", 29, 100.5f, true);

        ArrayList<Stu> stuList = new ArrayList<>();
        stuList.add(stu0);
        stuList.add(stu1);
        stuList.add(stu2);
        stuList.add(stu3);
        stuList.add(stu4);
        stuList.add(stu5);
        stuList.add(stu6);
        esTemplate.save(stuList);

        return "OK";
    }

}
