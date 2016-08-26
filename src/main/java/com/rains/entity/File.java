package com.rains.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.Serializable;

/**
 * Created by Administrator on 2016-08-23.
 */
public class File implements Serializable{

    private String name;
    private MultipartFile p1;
    private MultipartFile p2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getP1() {
        return p1;
    }

    public void setP1(MultipartFile p1) {
        this.p1 = p1;
    }

    public MultipartFile getP2() {
        return p2;
    }

    public void setP2(MultipartFile p2) {
        this.p2 = p2;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
