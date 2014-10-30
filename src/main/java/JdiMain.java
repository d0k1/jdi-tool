import com.sun.jdi.Bootstrap;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.VirtualMachineManager;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.tools.attach.AttachNotSupportedException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Denis V. Kirpichenkov on 30.10.14.
 */
public class JdiMain {

	public static String getPID() {
		String processName =
			java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
		return (processName.split("@")[0]);
	}

	public static void main(String[] args) throws IOException, AttachNotSupportedException, IllegalConnectorArgumentsException {
		System.out.println("Testing Java Debugger Interface");

		//connectToHost("localhost", "8000");
		//connectToProcess(getPID());
		connectToProcess2(getPID());

		System.out.printf("Done");
	}

	private static VirtualMachine connectToProcess(String pid) throws IOException, IllegalConnectorArgumentsException {
		VirtualMachineManager vmManager = Bootstrap.virtualMachineManager();

		AttachingConnector connector = null;

		for (Connector item : vmManager.attachingConnectors()) {

			if(item.name().endsWith("SAPIDAttachingConnector")){
				connector = (AttachingConnector) item;
			}

			System.out.println(item.name());
		}

		/**
		 * Linux feature:
		 *
		 * echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope
		 */

		Map<String, Connector.Argument> params = connector.defaultArguments();
		//params.get("pid").setValue("14071");
		params.get("pid").setValue(pid);
		VirtualMachine vm = connector.attach(params);
		return vm;
	}

	private static VirtualMachine connectToProcess2(String pid) throws IOException, IllegalConnectorArgumentsException {
		VirtualMachineManager vmManager = Bootstrap.virtualMachineManager();

		AttachingConnector connector = null;

		for (Connector item : vmManager.attachingConnectors()) {

			if(item.name().endsWith("ProcessAttach")){
				connector = (AttachingConnector) item;
			}

			System.out.println(item.name());
		}

		/**
		 * Linux feature:
		 *
		 * echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope
		 */

		Map<String, Connector.Argument> params = connector.defaultArguments();
		//params.get("pid").setValue("14071");
		params.get("pid").setValue(pid);
		VirtualMachine vm = connector.attach(params);
		return vm;
	}

	private static VirtualMachine connectToHost(String host, String port) throws IOException, IllegalConnectorArgumentsException {
		VirtualMachineManager vmManager = Bootstrap.virtualMachineManager();

		AttachingConnector connector = null;

		for (Connector item : vmManager.attachingConnectors()) {

			//if(item.name().endsWith("SAPIDAttachingConnector")){
			if(item.name().endsWith("SocketAttach")){
				connector = (AttachingConnector) item;
			}

			System.out.println(item.name());
		}
		/**
		 * Linux feature:
		 *
		 * echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope
		 */

		Map<String, Connector.Argument> params = connector.defaultArguments();
		params.get("hostname").setValue(host);
		params.get("port").setValue(port);

		VirtualMachine vm = connector.attach(params);
		return vm;
	}
}
