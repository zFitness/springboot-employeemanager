package cn.employee.manager.dto.result;

/**
 * 状态码枚举类
 * @author zfitness
 */
public enum ResponseCode {

	// 公共请求信息
	SUCCESS(200, "请求成功"),
	TABLE_SUCCESS(0, "请求成功"),
	FAIL(500, "程序错误,杀个程序员祭天"),
	USER_NOT_FOUND(404, "用户不存在"),
	PARAMETER_MISSING(600,"参数缺失"),
	UNAUTHORIZED(401,"未授权"),
	// ..一真往后面加

	//用户信息
	//5000100 - 5000200
	USERNAME_REPEAT(5000100,"用户名已存在"),
	PHONE_REPEAT(5000101,"手机号已存在"),
	EMAIL_REPEAT(5000102,"邮箱已存在"),
	//用户-角色
	//5000201 - 5000300
	USER_ROLE_NO_CLEAR(5000201,"该角色存在用户关联，无法删除");
	private Integer code;
	
	private String message;
	
	ResponseCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}



}
