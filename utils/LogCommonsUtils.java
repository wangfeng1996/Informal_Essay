package com.sinocarbon.polaris.commons.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogCommonsUtils {

	/**
	 * 输出业务日志通用类
	 * 
	 * @param logParams    日志对象
	 * @param topic        日志主题，为null则默认“audit-log”审计日志
	 * @param tenantId     租户id
	 * @param objectMapper json装配器
	 * @param logType 日志类型，默认是审计日志(json日志会被解析后存入es的数据)；如果不需要解析日志，则输入自己日志的类型
	 */
	public static void initOperationLogMessages(Map<String, Object> logParams, String topic, String tenantId,
			ObjectMapper objectMapper, String... logType) {
		if (topic == null || Constant.EMPTY.equals(topic)) {
			// 定义默认的审计日志主题
			topic = Constant.DEFAULT_TOPIC;
		}
		logParams.put(Constant.LOG_TOPIC, topic);
		// 放入租户id
		logParams.put(Constant.LOG_TENANTID, tenantId);
		// 业务日志输出
		Map<String, Object> operationLog = new HashMap<String, Object>();
		if (logType.length > 0) {
			operationLog.put(Constant.OPLOG, logType[0]);
		} else {
			operationLog.put(Constant.OPLOG, Constant.OPERATOR_LOG);
		}
		operationLog.put(Constant.OPERATRD_OBJECT, logParams);

		try {
			log.info(objectMapper.writeValueAsString(operationLog));
		} catch (JsonProcessingException e) {
			log.error("拼装审计日志信息错误：{" + e.getMessage() + "}");
		}
	}

}
