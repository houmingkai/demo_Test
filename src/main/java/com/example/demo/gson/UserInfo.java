package com.example.demo.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {

    //@Expose + gsonBuilder.excludeFieldsWithoutExposeAnnotation() 只序列化带@Expose的字段
    //@SerializedName 可以更改序列化的字段名称
    //这里要设置setVersion(3.2)，@Since标注的在 3.2 版本或之后才会输出，@Until标注的只在 3.2版本前才有

    @SerializedName("username")
    private String name;
    @Expose
    @Until(3.2)
    private String sex;
    @Since(3.2)
    private Integer age;
}
