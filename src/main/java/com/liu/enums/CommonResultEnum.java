package com.liu.enums;

import com.liu.base.BaseResult;

/**
 * @author zizai
 * @since 2022/9/1 11:20
 *公共返回结果代码和信息 实现公共返回接口
 **/
public enum CommonResultEnum implements BaseResult {

    /**
     * 公共信息
     */
    COMMON_SUCCESS("0", "请求成功"),
    COMMON_FAILED("-1", "请求失败"),

    COMMON_SYS_ERROR("1000", "系统运行时错误"),
    COMMON_EMPTY_PARAM("1001", "参数为空"),
    COMMON_INVALID_PARAM("1002", "参数格式不正确"),
    COMMON_FAILED_TO_ACHIEVE_DATA("1003", "获取数据失败"),
    COMMON_INVALID_DATA_FORMAT("1004", "数据格式有误"),
    COMMON_DATABASE_OPERATION_FAILED("1005", "数据库操作失败"),
    COMMON_IO_OPERATION_FAILED("1006", "IO操作失败"),
    COMMON_RPC_INVOCATION_FAILED("1007", "远程调用失败"),
    COMMON_RPC_EMPTY_RESPONSE("1008", "远程调用返回为空"),
    COMMON_INVALID_DATA("1009", "数据错误"),
    COMMON_DATA_NOT_EXIST("1010", "数据不存在"),
    COMMON_NO_CORRESPONDING_DISTRICT("1011", "未找到对应区域"),
    COMMON_EMPTY_DATA("1012", "数据为空"),
    COMMON_INVALID_CLIENT_CONFIG("1013", "客户端配置错误"),
    COMMON_FAILED_TO_PUSH_MESSAGE("1014", "消息推送失败"),
    COMMON_HTTP_REQUEST_FAILED("1015", "Http请求失败"),
    COMMON_ENCODING_FAILED("1016", "编码失败"),
    COMMON_INVALID_DATE_FORMAT("1017", "日期格式错误: {0}"),
    COMMON_PROHIBIT_DELETE("1018", "数据禁止被删除"),
    COMMON_CLIENT_ID_ERROR("1019", "clientId错误"),
    COMMON_NO_CORRESPONDING_BEAN("1020", "找不到相关的bean"),
    COMMON_UNABLE_TO_CONVERT_UNIT("1021", "无法进行单位转换"),
    COMMON_INVALID_UNIT("1022", "无效计量单位"),
    COMMON_DATA_DUPLICATE("1023", "数据重复"),
    COMMON_CODE_EXIST("1024", "code已存在"),
    COMMON_FILE_UPLOAD("1025", "上传文件失败"),
    COMMON_SIMCARD_ICCIDLENGTH("1026", "iccid只能为19-20位的数字"),
    COMMON_EMIAL_ERROR("1027", "输入的邮箱有误"),
    COMMON_PHONE_ERROR("1028", "输入的手机号码有误"),
    COMMON_EMPTY_CAPTCHA("1029", "请输入验证码"),
    COMMON_CAPTCHA_ERROR("1030", "输入的验证码有误"),
    COMMON_EMPTY_BUSINESS("1031", "用户不存在"),
    COMMON_BUSINESS_ERROR("1032", "账号或密码错误"),
    COMMON_PHONE_EXISTS_ERROR("1033", "手机号已存在"),
    COMMON_PHONE_NOT_EXISTS_ERROR("1034", "手机号不存在"),
    COMMON_BUSINESS_FORBIDDEN("1035", "账号已被禁用，请联系管理员"),
    COMMON_ROLE_NAME_EXISTS_ERROR("1036", "角色名称已存在"),
    COMMON_BUSINSEE_CODE_EMPTY_ERROR("1037", "资源id为空"),
    COMMON_TIME_ERROR("1038","起始时间大于结束时间"),
    COMMON_NUMBER_EXISTS_ERROR("1039", "编号已存在"),
    COMMON_TIME_FORMAT("1040", "时间转换错误"),
    COMMON_FILE_EXISTS_ERROR("1041", "文件已存在"),
    COMMON_WATER_PHENOLOGY_ERROR("1042", "物候期数据不存在"),
    COMMON_PHENOLOGY_KC_ERROR("1043", "物候期KC数据不存在"),
    COMMON_WATER_DIC_ERROR("1044", "计算用到字典值缺失"),
    COMMON_MOISTURE_NO_DATA_ERROR("1045", "传感器暂无数据"),
    COMMON_FERTIGATION_RECOMMEND_ERROR("1046", "无法计算肥料推荐用量"),
    COMMON_WATER_ZERO_ERROR("1047", "灌溉计算参数不能为0"),
    COMMON_GET_METEOROLOGY_DATA_ERROR("1048", "无法获取气象数据"),
    COMMON_LON_LAT_ERROR("1049", "输入的经纬度有误"),


    COMMON_TOKEN_NOT_EXISTS_ERROR("104001", "认证失败或已失效,请重新登录！"),

    COMMON_INVALID_LOGINNAME_OR_PASSWORD("104002", "用户名或密码错误"),
    COMMON_TOKEN_EXPIRED("104003", "token过期"),
    COMMON_INVALID_TOKEN("104004", "无效token"),
    COMMON_EMPTY_TOKEN("104005", "token为空"),
    COMMON_PERMISSION_DENY("104006", "权限不足"),
    COMMON_INVALID_VERIFY_CODE("104007", "无效验证码"),
    COMMON_SMS_SEND_EXCEED("1040016", "短信发送超出频次"),
    COMMON_VERIFY_CODE_NOT_FOUND("104017", "请先获取验证码"),

    COMMON_DEL_ORG_ERROR_FOR_DEVICE_NOT_EMPTY("104008", "删除失败，该组织下存在设备"),
    COMMON_DEL_ORG_ERROR_FOR_USER_NOT_EMPTY("104009", "删除失败，该组织下存在用户"),
    COMMON_DEL_ORG_ERROR_FOR_SUB_ORG_NOT_EMPTY("104010", "删除失败，该组织下存在子组织"),
    COMMON_DEL_BUSINESS_ERROR_FOR_ORG_NOT_EMPTY("104011", "删除失败，该项目下存在组织"),
    COMMON_DEL_BUSINESS_ERROR_FOR_USER_NOT_EMPTY("104012", "删除失败，该项目下存在用户"),
    COMMON_DEL_BUSINESS_ERROR_FOR_EQU_NOT_EMPTY("104013", "删除失败，该项目下存在设备"),
    COMMON_ERROR_FOR_NAME_EXIST("104014", "操作失败，名称重复"),
    COMMON_ERROR_FOR_MOBILE_EXIST("104015", "操作失败，手机号重复"),
    SOIL_EXPORT_ERROR("104016", "时间跨度超过上限，无法导出"),

    NUTRITION_PARAM_ERROR("110001","灌溉参数配置有误，请重新配置"),
    NUTRITION_CROP_DEPTH_ERROR("110002","植物根深未查询到，请联系管理员配置"),
    NUTRITION_METEOROLOGY_ERROR("110003","气象数据查询失败"),
    NUTRITION_SOWING_TIME_ERROR("110004","播种期有误"),
    NUTRITION_DELTAZHI_LESS_HF("110005","实际水损HF小于△H支,不符合请重新配置!"),
            ;


    /**
     * 返回信息代码
     */
    private String code;

    /**
     * 返回信息内容
     */
    private String message;

    /**
     * constructors
     */
    CommonResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * getters
     */
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
