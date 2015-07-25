#!/bin/sh

JAVA_HOME=/usr/lib/jvm/java-8-oracle

if [ -z "$1" ]; then
    echo "pass app PID as first parameter"
    exit 1
fi

echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope

java -cp ${JAVA_HOME}/lib/tools.jar:${JAVA_HOME}/lib/sa-jdi.jar:jdi-sa-heap-walk.jar sun.jvm.hotspot.HSDB