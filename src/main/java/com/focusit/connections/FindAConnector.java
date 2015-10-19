package com.focusit.connections;

import java.util.List;

import com.sun.jdi.Bootstrap;
import com.sun.jdi.connect.Connector;

public class FindAConnector {

	public static void main(String argsp[]) {
		System.out.println("Listing available connector to target VM");
		List<Connector> connectors = Bootstrap.virtualMachineManager().allConnectors();
		for (Connector connector : connectors) {
			System.out.println(connector.name()+" - "+connector.description());
		}
	}
}
