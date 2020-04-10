package com.example.bbcb.exception;

import lombok.Getter;

public enum BusinessExceptionStatusEnum{
    /*自定义错误码
    * 0 成功
    * 1xx 安全校验类错误
    * 2xx 数据异常
    * 3xx 缓存异常
    * 4xx 客户端错误，比如客户端给的参数错误等
    * 5xx 服务器端错误，比如操作数据库错误等
    * 6xx 未知错误
     */
    Success(0,"成功"),
    SuccessButDataEmpty(0,"成功但是数据为空"),
    CreateSuccess(0,"添加成功"),
    UpdateSuccess(0,"更新成功"),
    QuerySuccess(0,"查询成功"),
    DeleteSuccess(0,"删除成功"),
    SecurityAuthError(1000,"安全校验错误"),
    UserNotLoginError(101,"用户未登录"),
    VerificationFailError(102,"校验失效"),
    CheckSumInvalidError(103,"验证码错误"),
    PermissionInsufficientError(104,"权限不足"),
    UserNotExistError(105,"用户不存在"),
    DataException(2000,"数据异常"),
    DataExistException(201,"数据已经存在"),
    DataEmptyException(202,"数据为空"),
    FileNotFoundException(203,"文件不存在"),
    FileExistException(204,"文件已经存在"),
    FileContentEmptyException(205,"空文件"),
    DataContentLengthTooLong(206,"数据内容过长"),
    DataContentLengthTooShortError(207,"数据内容太短"),
    EncryptedKeyError(208,"秘钥异常"),
    EncryptedFailed(209,"加密失败"),
    DecryptedFailed(210,"解密失败"),
    MD5Failed(211,"md5失败"),
    JsonFormatErr(212,"json数据格式错误"),
    DirectoryCreateFailed(213,"文件夹创建失败"),
    DirectoryExistErr(214,"文件夹已经存在"),
    CacheError(3000,"缓存访问异常"),
    ClientError(4000,"客户端错误"),
    UserNameOrPasswordError(401,"用户名或密码错误"),
    ParametersLackError(402,"缺少参数"),
    ParametersWrongError(403,"参数错误"),
    ServerError(5000,"服务器端错误"),
    MySqlDBError(500,"数据库错误"),
    RedisError(520,"redis错误"),
    MongoDBError(540,"mongodb错误"),
    HttpRequstException(560,"http请求异常"),
    FileUploadError(203,"文件不存在"),
    UnkownExceptionError(6000,"未知错误");


    @Getter
    private int code;//异常状态码
    @Getter
    private String msg;//异常状态码

    BusinessExceptionStatusEnum(int code,String msg){
        this.code=code;
        this.msg=msg;
    }


}
