#JDI tools

Java runtime diagnostic tools based on jdi/serviceability api

# VMCapabilities
VMCapabilitiesProvider show what you can do with running JVM using different ways to connect.
Moreover it prints to stdout all loaded classes and all alive threads.

Examples

To use ProcessAttach mechanism
`java -cp ${JAVA_HOME}/lib/tools.jar:${JAVA_HOME}/lib/sa-jdi.jar:jdi-sa-tools.jar com.focusit.capabilities.VMCapabilitiesProvider -c pid -p $JVM_PID`

To use Serviceability API
`java -cp ${JAVA_HOME}/lib/tools.jar:${JAVA_HOME}/lib/sa-jdi.jar:jdi-sa-tools.jar com.focusit.capabilities.VMCapabilitiesProvider -c sapid -p $JVM_PID`

#HeapWalker
Simple demonstration heap walking ability. This sample writes to stdout some sort of heap histogram as csv. 

Example
`simple_heap_walk_jdk8.sh 6213 > output.csv`

#Hotspot Debugger
In some cases you might need a "super power" over JVM, it is where JDK's internal (hotspot) debugger shines.
It uses special connector to JVM and can show you anything from JVM under debugger.
And to remind first of all to myself hot to run it I made trivial scripts to run HSDB with GUI for JDK7 and JDK8: HSDB_7.sh, HSDB_8.sh

