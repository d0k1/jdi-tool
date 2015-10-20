package com.focusit.capabilities;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.focusit.connections.ConnectorProvider;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;

/**
 * Uses either sun.jfinal boolean hotspot.jdi.SAPIDAttachingConnector or com.sun.jdi.ProcessAttach to attach to a VM and show available capabilities 
 * @author doki
 *
 */
public class VMCapabilitiesProvider {

	enum Connectors {
		pid, sapid;
	};
	
	public static VMCapabilities getCapabilities(VirtualMachine vm)
	{
		return new VMCapabilities(vm.canAddMethod(), vm.canBeModified(), vm.canForceEarlyReturn(), vm.canGetBytecodes(), vm.canGetClassFileVersion(), vm.canGetConstantPool(), vm.canGetCurrentContendedMonitor(), vm.canGetInstanceInfo(), vm.canGetMethodReturnValues(), vm.canGetMonitorFrameInfo(), vm.canGetMonitorInfo(), vm.canGetOwnedMonitorInfo(), vm.canGetSourceDebugExtension(), vm.canGetSyntheticAttribute(), vm.canPopFrames(), vm.canRedefineClasses(), vm.canRequestMonitorEvents(), vm.canRequestVMDeathEvent(), vm.canUnrestrictedlyRedefineClasses(), vm.canUseInstanceFilters(), vm.canUseSourceNameFilters(), vm.canWatchFieldAccess(), vm.canWatchFieldModification());
	}

	public static VMCurrentStatus getCurrentStatus(VirtualMachine vm)
	{
		return new VMCurrentStatus(vm.allClasses(), vm.allThreads());
	}
	
	public static void main(String[] args) throws ParseException, IOException, IllegalConnectorArgumentsException{
		Option connectorOption = new Option("c", "connector", true, "Connector to use");
		connectorOption.setArgs(1);
		connectorOption.setOptionalArg(false);
		connectorOption.setArgName("Connector to use");
		
		Option pidOption = new Option("p", "pid", true, "Proccess ID to connect to");
		pidOption.setArgs(1);
		pidOption.setOptionalArg(false);
		pidOption.setArgName("Proccess ID to connect to");
		
		Options posixOptions = new Options();
		posixOptions.addOption(connectorOption);
		posixOptions.addOption(pidOption);
		
		CommandLineParser cmdLinePosixParser = new DefaultParser();

		CommandLine commandLine = cmdLinePosixParser.parse(posixOptions, args);
		
		Option opts[] = commandLine.getOptions();
		
		if(opts.length<2) {
			System.err.println("Required two options but "+opts.length+" was passed!");
			System.exit(1);
		}
		
		Connectors connector = null;
		String pid = null;
		
		for(Option opt:opts){
			if(opt.getArgName()==connectorOption.getArgName()){
				connector = Connectors.valueOf(opt.getValue());
			}
			
			if(opt.getArgName()==pidOption.getArgName()){
				pid = opt.getValue();
			}
		}
		
		if(pid==null || connector==null){
			System.err.println("Error parsing args");
			System.exit(1);
		}
		
		VirtualMachine vm = null;
		if(connector==Connectors.pid){
			vm = ConnectorProvider.connectPID(pid);
		}
		
		if(connector==Connectors.sapid){
			vm = ConnectorProvider.connectSAPID(pid);
		}
	
		System.out.println(vm.description());
		
		System.out.println(getCapabilities(vm));
		
		VMCurrentStatus status = getCurrentStatus(vm);
		System.out.println("Loaded classes:");
		status.printAllClasses();
		
		System.out.println("Threads:");
		status.printAllThread();
	}
}
