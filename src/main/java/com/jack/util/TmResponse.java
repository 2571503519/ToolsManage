package com.jack.util;

/**
 * Created by Jackaroo Zhang on 2018/10/11.
 */

import lombok.Getter;

/**
 * Web层返回的响应对象，用来承载请求的结果状态和数据
 */
public class TmResponse<T> {

    static class TmState {
        // 响应状态的状态码
        @Getter private Integer code;
        // 响应状态的描述信息
        @Getter private String msg;

        public TmState(StateCode code, String msg) {
            this.code = code.code;
            this.msg = msg;
        }
    }

    enum StateCode {
        // 响应的状态码，根据需求后面会继续添加几种状态
        SUCCESS(200), FAIL(404);
        private Integer code;
        StateCode(Integer code) {
            this.code = code;
        }
    }

    // 响应状态对象
    @Getter private TmState tmState;
    // 响应的数据，T为泛型
    @Getter private T result;

    /**
     * 响应对象一般通过静态方法构造，所以将构造方法私有化
     * @param tmState
     * @param result
     */
    private TmResponse(TmState tmState, T result) {
        this.tmState = tmState;
        this.result = result;
    }

    private TmResponse(TmState tmState) {
        this.tmState = tmState;
        this.result = null;
    }

    /**
     * 构造一个成功状态的响应
     * @param msg 响应状态的描述信息
     * @param result 响应的数据
     * @param <T> 响应数据的类型
     * @return
     */
    public static <T> TmResponse success(String msg, T result) {
        return new TmResponse(new TmState(StateCode.SUCCESS, msg), result);
    }

    public static TmResponse success(String msg) {
        return new TmResponse(new TmState(StateCode.SUCCESS, msg));
    }

    /**
     * 构造一个失败状态的响应，请求了一个不存在的资源
     * @param msg
     * @param result
     * @param <T>
     * @return
     */
    public static <T> TmResponse fail(String msg, T result) {
        return new TmResponse(new TmState(StateCode.FAIL, msg), result);
    }

    public static <T> TmResponse fail(String msg) {
        return new TmResponse(new TmState(StateCode.FAIL, msg));
    }

}
