package com.focusit.connections;

import java.io.IOException;
import java.util.Map;

import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;

public class ConnectorProvider {
	
	/**
	 * Can't debug itself process.
	 * Uses pid to attach to process to get socket connection parameters and uses socket to communicate.
	 * if debugee process hasn't been started with "-agentlib:jdwp=transport=dt_socket" connection fails
	 * @param pid
	 * @return
	 * @throws IllegalConnectorArgumentsException 
	 * @throws IOException 
	 */
	public static VirtualMachine connectPID(String pid) throws IOException, IllegalConnectorArgumentsException{
		AttachingConnector connector = null;
		connector = (AttachingConnector) FindAConnector.getConnectorByName("ProcessAttach");
		Map<String, Connector.Argument> params = connector.defaultArguments();
		params.get("pid").setValue(pid);
		VirtualMachine vm = connector.attach(params);
		return vm;
	}
	
	/**
	 * Can debug itself process. Jvm should be started with options
	 * Should have enough permissions 
	 * 
	 * for linux the simplest way to get then is to execute cmd "echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope"
	 * 
	 * @param pid
	 * @return
	 * @throws IllegalConnectorArgumentsException 
	 * @throws IOException 
	 */
	public static VirtualMachine connectSAPID(String pid) throws IOException, IllegalConnectorArgumentsException{
		AttachingConnector connector = null;
		connector = (AttachingConnector) FindAConnector.getConnectorByName("SAPIDAttachingConnector");
		Map<String, Connector.Argument> params = connector.defaultArguments();
		params.get("pid").setValue(pid);
		VirtualMachine vm = connector.attach(params);
		return vm;
	}

	
	/**
	 * -agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=n
	 * @param pid
	 * @return
	 * @throws IllegalConnectorArgumentsException 
	 * @throws IOException 
	 */
	public static VirtualMachine connectSocket(String host, String port) throws IOException, IllegalConnectorArgumentsException{
		AttachingConnector connector = null;
		connector = (AttachingConnector) FindAConnector.getConnectorByName("SocketAttach");
		Map<String, Connector.Argument> params = connector.defaultArguments();
		params.get("hostname").setValue(host);
		params.get("port").setValue(port);

		VirtualMachine vm = connector.attach(params);
		return vm;
	}
}
