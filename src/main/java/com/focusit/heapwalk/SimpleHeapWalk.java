package com.focusit.heapwalk;

import com.sun.jdi.Bootstrap;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.VirtualMachineManager;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.tools.attach.AttachNotSupportedException;
import sun.jvm.hotspot.HotSpotAgent;
import sun.jvm.hotspot.memory.SystemDictionary;
import sun.jvm.hotspot.oops.HeapVisitor;
import sun.jvm.hotspot.oops.Klass;
import sun.jvm.hotspot.oops.Oop;
import sun.jvm.hotspot.runtime.VM;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Denis V. Kirpichenkov on 30.10.14.
 */
public class SimpleHeapWalk {
	public static String getPID() {
		String processName =
			java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
		return (processName.split("@")[0]);
	}

	public static VM attachSAAgent(String pid) {
		HotSpotAgent agent = new HotSpotAgent();
		agent.attach(Integer.parseInt(pid));

		return VM.getVM();
	}

	static class heapKlassData {
		public long l = 0;
		public long count = 0;
		public long size = 0;
	}

	public static void main(String[] args) throws IOException, AttachNotSupportedException, IllegalConnectorArgumentsException, InterruptedException {
		System.err.println("Testing Java Debugger Interface");

		final VM vm = attachSAAgent(args[0]);

		AtomicLong total = new AtomicLong(0);

		final Map<Klass, heapKlassData> histo = new HashMap<>();

		vm.getSystemDictionary().classesDo(new SystemDictionary.ClassVisitor() {
			@Override
			public void visit(final Klass klass) {
				//System.out.println("Klass: " + klass.signature());
				histo.put(klass, new heapKlassData());
			}
		});

		System.err.println("Used:" + vm.getUniverse().heap().used());

		vm.getObjectHeap().iterate(new HeapVisitor() {
			@Override
			public void prologue(long l) {
				System.err.println("Prolog: " + l);
			}

			@Override
			public boolean doObj(Oop oop) {
				Klass k = oop.getKlass();
				heapKlassData d = histo.get(k);
				if (d != null) {
					d.count++;
					d.size += oop.getObjectSize();
				}
				return false;
			}

			@Override
			public void epilogue() {
			}
		});

		long summ = 0;
		long count = 0;
		for (Map.Entry<Klass, heapKlassData> entry : histo.entrySet()) {
			summ += entry.getValue().size;
			count += entry.getValue().count;

			if (entry.getValue().count <= 0)
				continue;

			System.out.println(entry.getKey().signature() + entry.getValue().count + ";" + entry.getValue().size);
		}

		System.err.printf("Processed: " + count + " Summ: " + summ);
	}

	/**
	 * Cannot debug itself
	 *
	 * @param pid
	 * @return
	 *
	 * @throws IOException
	 * @throws IllegalConnectorArgumentsException
	 */
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

	/**
	 * Can debug itself process. Jvm should be started with options
	 * -agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=n
	 * @param pid
	 * @return
	 * @throws IOException
	 * @throws IllegalConnectorArgumentsException
	 */
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

	/**
	 * -agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=n
	 *
	 * @param host
	 * @param port
	 * @return
	 * @throws IOException
	 * @throws IllegalConnectorArgumentsException
	 */
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
