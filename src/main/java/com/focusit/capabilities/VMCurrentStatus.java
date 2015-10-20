package com.focusit.capabilities;

import java.util.List;

import com.sun.jdi.ReferenceType;
import com.sun.jdi.ThreadReference;

public class VMCurrentStatus {
	public final List<ReferenceType> allClasses;
	public final List<ThreadReference> allThreads;
	
	public VMCurrentStatus(List<ReferenceType> classes, List<ThreadReference> threads){
		this.allClasses = classes;
		this.allThreads = threads;
	}
	
	public void printAllClasses(){
		for(ReferenceType type:allClasses){
			System.out.println(type.name());
		}
	}

	public void printAllThread(){
		for(ThreadReference type:allThreads){
			System.out.println(type.toString());
		}
	}
}
