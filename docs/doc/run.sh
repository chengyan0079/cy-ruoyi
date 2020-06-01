#!/bin/bash
APP_NAME=ruoyi-provider-app

# SkyWalking Agent 配置
# 配置 Agent 名字。一般来说，我们直接使用 Spring Boot 项目的 `spring.application.name`
export SW_AGENT_NAME=${APP_NAME}
# 配置 Collector 地址
export SW_AGENT_COLLECTOR_BACKEND_SERVICES=139.155.70.19:11800
# 配置链路的最大 Span 数量。一般情况下，不需要配置，默认为 300 。主要考虑，有些新上 SkyWalking Agent 的项目，代码可能比较糟糕
export SW_AGENT_SPAN_LIMIT=2000
# SkyWalking Agent jar 地址
JAVA_AGENT="-javaagent:/home/chengy/jar/provider/agent/skywalking-agent.jar"
# JVM
JVM_OPS="-server -XX:+HeapDumpOnOutOfMemoryError -Xms128m -Xmx256m -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=128m"

echo "任务开始：${APP_NAME}"

usage() {
	echo "error:sh run.sh [start | stop | restart | status]"
	echo "请类似这样执行 sh run.sh start or sh run.sh restart"
	exit 1
}

# 判断当前服务是否已经启动的函数
is_exist() {
	echo "执行is_exist方法"
	pid=`ps -ef|grep ${APP_NAME}|grep -v grep|awk '{print $2}'`
	if [ -z "${pid}" ]; then
		echo "pid is null."
		return 1
	else
		echo "${APP_NAME} running. pid=${pid}"
		return 0
	fi
}
#启动服务命令
start() {
	echo "执行start方法"
	is_exist
	if [ $? -eq 0 ]; then
		echo "${APP_NAME} running. pid=${pid}"
	else
		echo "JVM参数：${JVM_OPS}"
		echo "Skywalking：${JAVA_AGENT}"
		
	    nohup java ${JVM_OPS} ${JAVA_AGENT} -jar ${APP_NAME}.jar --spring.profiles.active=dev &

		echo "${APP_NAME} started"
	fi
}
#停止服务命令
stop() {
	echo "执行stop方法"
	is_exist
	if [ $? -eq 0 ]; then
		kill -9 $pid
		echo "${pid} stop"
	else
		echo "${APP_NAME} not running"
	fi
}

#重启命令
restart() {
	stop
	start
}
case "$1" in
	"status")
		is_exist
		;; 
	"start")
		start
		;;
	"stop")
		stop
		;;
	"restart")
		restart
		;;
	*)
		usage
		;;
esac
