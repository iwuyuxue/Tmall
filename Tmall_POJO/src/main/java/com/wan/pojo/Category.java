package com.wan.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 万星明
 * @Date 2019/2/16
 */
@Entity //表明该类为实体类
@Table(name = "category") //对应的数据表
@Data
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    String name;

}
