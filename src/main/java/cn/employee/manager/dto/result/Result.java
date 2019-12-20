package cn.employee.manager.dto.result;

import lombok.Data;

import java.io.Serializable;


@Data
public class Result implements Serializable {
    //返回状态码
    Integer code;
    //返回消息
    String msg;
    //返回数据
    Object data;

    public Result() {
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setResultCode(ResponseCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    }

    //操作成功, 不返回数据
    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResponseCode.SUCCESS);
        return result;
    }

    //操作成功， 且返回数据
    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResponseCode.SUCCESS);
        result.setData(data);
        return result;
    }

    //操作失败,
    public static Result failure() {
        Result result = new Result();
        result.setResultCode(ResponseCode.FAIL);
        return result;
    }

    //操作失败, 不返回数据
    public static Result failure(ResponseCode responseCode) {
        Result result = new Result();
        result.setResultCode(responseCode);
        return result;
    }

    //操作失败, 返回数据
    public static Result failure(ResponseCode responseCode, Object data) {
        Result result = new Result();
        result.setResultCode(responseCode);
        result.setData(data);
        return result;
    }

}
