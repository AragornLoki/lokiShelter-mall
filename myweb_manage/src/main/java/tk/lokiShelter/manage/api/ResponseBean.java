package tk.lokiShelter.manage.api;

import tk.lokiShelter.manage.common.ResultCode;

import java.util.HashMap;
import java.util.Map;

public class ResponseBean<T> {
    private long code;
    private String message;
    private T data;

    public ResponseBean() {
    }

    public ResponseBean(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public ResponseBean(long code, String messagea) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public static Object ok() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", ResultCode.SUCCESS.getCode());
        obj.put("message", "成功");
        return obj;
    }
    public static <T> ResponseBean<T> success(T data) {
        return new ResponseBean<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static ResponseBean ok(Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        return new ResponseBean(200,"成功",data);
    }


//    public static Object okList(List list) {
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put("list", list);
//
//        if (list instanceof Page) {
//            Page page = (Page) list;
//            data.put("total", page.getTotal());
//            data.put("page", page.getPageNum());
//            data.put("limit", page.getPageSize());
//            data.put("pages", page.getPages());
//        } else {
//            data.put("total", list.size());
//            data.put("page", 1);
//            data.put("limit", list.size());
//            data.put("pages", 1);
//        }
//
//        return ok(data);
//    }
//
//    public static Object okList(List list, List pagedList) {
//        Map<String, Object> data = new HashMap<String, Object>();
//        data.put("list", list);
//
//        if (pagedList instanceof Page) {
//            Page page = (Page) pagedList;
//            data.put("total", page.getTotal());
//            data.put("page", page.getPageNum());
//            data.put("limit", page.getPageSize());
//            data.put("pages", page.getPages());
//        } else {
//            data.put("total", pagedList.size());
//            data.put("page", 1);
//            data.put("limit", pagedList.size());
//            data.put("pages", 1);
//        }
//
//        return ok(data);
//    }

    public static ResponseBean fail() {
        return new ResponseBean(-1,"错误");
    }

    public static Object fail(int errno, String errmsg) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", errno);
        obj.put("message", errmsg);
        return obj;
    }

    public static <T> ResponseBean<T> unauthorized(T data) {
        return new ResponseBean<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }
    public static Object badArgument() {
        return fail(401, "参数不对");
    }

    public static Object badArgumentValue() {
        return fail(402, "参数值不对");
    }

    public static Object unlogin() {
        return fail(501, "请登录");
    }

    public static Object serious() {
        return fail(502, "系统内部错误");
    }

    public static Object unsupport() {
        return fail(503, "业务不支持");
    }

    public static Object updatedDateExpired() {
        return fail(504, "更新数据已经失效");
    }

    public static Object updatedDataFailed() {
        return fail(505, "更新数据失败");
    }

    public static Object unauthz() {
        return fail(506, "无操作权限");
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

