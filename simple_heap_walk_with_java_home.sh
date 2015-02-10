#!/bin/sh

if [ -z "$JAVA_HOME" ]; then
    echo "Need to set JAVA_HOME to point to jdk installation directory"
    exit 1
fi
if [ -z "$1" ]; then
    echo "pass app PID as first parameter"
    exit 1
fi

java -cp ${JAVA_HOME}/lib/tools.jar:${JAVA_HOME}/lib/sa-jdi.jar:jdi-sa-heap-walk.jar com.focusit.heapwalk.SimpleHeapWalk $1