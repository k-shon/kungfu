package top.annokshon.kungfu.utils;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 数据返回格式化类
 * 200：表示成功
 * 500：表示错误，错误信息存在msg字段中
 * 501：bean验证错误，不管多少个错误都以Map形式返回
 * 502：拦截器拦截到用户token出错 
 * 555：异常抛出信息
 */
public class JSONResult {
	
	//定义jackson对象
	private static final ObjectMapper mapper = new ObjectMapper();
	//响应业务状态
	private Integer status;
	//响应消息
	private String msg;
	//响应中的数据
	private Object data;
	//不使用
	private String ok;
	
	//构造方法
	public JSONResult(){}
	
	public JSONResult(Integer status,String msg,Object data){
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public JSONResult(Object data){
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}
	//是否成功
	public  Boolean isOK(){
		return this.status == 200;
	}
	//建立
	public static JSONResult build(Integer status,String msg,Object data){
		return new JSONResult(status,msg,data);
	}
	//status = 200
	public static JSONResult ok(Object data){
		System.out.println("返回数据："+data);
		return new JSONResult(data);
	}
	public static JSONResult ok(){
		return new JSONResult("");
	}
	//status = 500
	public static JSONResult errorMsg(String msg){
		return new JSONResult(500,msg,null);
	}
	//status = 501
	public static JSONResult errorMap(Object data){
		return new JSONResult(501,"error",data);
	}
	//status = 502
	public static JSONResult errorTokenMsg(String msg){
		return new JSONResult(502,msg,null);
	}
	//status = 555
	public static JSONResult errorException(String msg){
		return new JSONResult(555,msg, null);
	}
	
	/*
	 * 将json结果集转化为JSONResult对象需要转换的对象是一个类
	 */
	public static JSONResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return mapper.readValue(jsonData, JSONResult.class);
            }
            JsonNode jsonNode = mapper.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = mapper.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = mapper.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
	/*
	 * 没有object对象的转化
	 */
	public static JSONResult format(String json) {
        try {
            return mapper.readValue(json, JSONResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	/*
	 * Object是集合转化,需要转换的对象是一个list
	 */
	public static JSONResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = mapper.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = mapper.readValue(data.traverse(),
                        mapper.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getOk() {
		return ok;
	}
	public void setOk(String ok) {
		this.ok = ok;
	}
}
