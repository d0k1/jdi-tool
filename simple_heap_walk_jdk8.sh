#!/bin/sh

export JAVA_HOME=/usr/lib/jvm/java-8-oracle

if [ -z "$1" ]; then
    echo "pass app PID as first parameter"
    exit 1
fi

java -cp ${JAVA_HOME}/lib/tools.jar:${JAVA_HOME}/lib/sa-jdi.jar:jdi-sa-tools.jar com.focusit.heapwalk.SimpleHeapWalk $1