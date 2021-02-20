package com.example.bbcb.dto;

import com.example.bbcb.validators.ParamsValidatorAnnotation;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User {
    private Long id;

    private String uid;

    private String nickName;

    private  String password;

    private String secretKey;

    private Integer gender;

    private String avatar;

    private String birthday;

    private String deviceId;
    @ApiModelProperty("属性的类型；0->规格；1->参数")
    private Integer powerType;

    @NotEmpty(message = "属性分类不能为空")
    private String phoneNumber;

    private Timestamp createTime;

    private Timestamp updateTime;
}
