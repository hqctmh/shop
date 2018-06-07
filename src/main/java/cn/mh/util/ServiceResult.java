package cn.mh.util;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务返回结果
 * 
 * @author JBH
 *
 */
public class
ServiceResult extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public ServiceResult() {
		this.put("succeed", false);
		this.put("message", "");
		this.put("code", 0);
		this.put("recordCount", 0);
		this.put("pageCount", 0);
		this.put("pageSize", 0);
		this.put("pageNum", 0);
	}

	@Deprecated
	@Override
	public Object get(Object key) {
		return super.get(key);
	}

	@Deprecated
	@Override
	public Object put(String key, Object value) {
		return super.put(key, value);
	}

	@Deprecated
	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		super.putAll(m);
	}

	public boolean succeed() {
		return (Boolean) this.get("succeed");
	}

	public void setSucceed() {
		this.put("succeed", true);
	}

	public void setException(Exception e) {
		this.put("succeed", false);
		this.put("code", -1);
		this.put("message", "服务异常");
	}

	public ServiceResult setError(int code, String message) {
		this.put("succeed", false);
		this.put("code", code);
		this.put("message", message);
		return this;
	}

	/**
	 * 错误代码 负值：异常，0：无效，正值：错误
	 */
	public int getCode() {
		return (Integer) this.get("code");
	}

	/**
	 * 创建分页
	 * 
	 * @param recordCount
	 *            总记录数
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            每页显示数量
	 * @return
	 */
	public int createPage(int recordCount, int pageNum, int pageSize) {
		this.put("recordCount", recordCount);
		this.put("pageSize", pageSize);
		this.put("pageNum", pageNum);
		this.put("pageCount", recordCount % pageSize > 0 ? recordCount / pageSize + 1 : recordCount / pageSize);
		int rowIndex = (pageNum - 1) * pageSize;
		return rowIndex;
	}

	/**
	 * 总记录数
	 * 
	 * @return
	 */
	public int getRecordCount() {
		return (Integer) this.get("recordCount");
	}

	/**
	 * 总页数
	 * 
	 * @return
	 */
	public int getPageCount() {
		return (Integer) this.get("pageCount");
	}

	/**
	 * 每页显示数量
	 * 
	 * @return
	 */
	public int getPageSize() {
		return (Integer) this.get("pageSize");
	}

	/**
	 * 当前页码
	 * 
	 * @return
	 */
	public int getPageNum() {
		return (Integer) this.get("pageNum");
	}

	/**
	 * 信息
	 */
	public String getMessage() {
		return (String) this.get("message");
	}

	@SuppressWarnings("rawtypes")
	public void setAttribute(String key, Object value) {
		if (value instanceof List) {
			this.put(key, ((List) value).toArray());
		} else {
			this.put(key, value);
		}
	}

	public String getString(String key) {
		return (String) this.get(key);
	}

	public int getInteger(String key) {
		return (Integer) this.get(key);
	}

	public double getDouble(String key) {
		return (Double) this.get(key);
	}

	public float getFloat(String key) {
		return (Float) this.get(key);
	}

	public <T> T getEntity(String key, Class<T> clazz) {
		Object value = this.get(key);
		if (value != null) {
			return clazz.cast(value);
		}
		return null;
	}

	public <T> List<T> getList(String key, Class<T> clazz) {
		Object values = this.get(key);
		if (values != null) {
			Object[] arr = (Object[]) values;
			List<T> list = new ArrayList<T>();
			for (Object obj : arr) {
				list.add(clazz.cast(obj));
			}
			return list;
		}
		return null;
	}

	public Object getObject(String key) {
		return this.get(key);
	}

	/**
	 * 返回JSON格式字符串
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		System.out.println(list.getClass());
	}

}
