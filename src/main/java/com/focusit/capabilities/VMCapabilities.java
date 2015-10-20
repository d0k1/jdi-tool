package com.focusit.capabilities;

public class VMCapabilities {
	final boolean canAddMethod;
	final boolean canBeModified;
	final boolean canForceEarlyReturn;
	final boolean canGetBytecodes;
	final boolean canGetClassFileVersion;
	final boolean canGetConstantPool;
	final boolean canGetCurrentContendedMonitor;
	final boolean canGetInstanceInfo;
	final boolean canGetMethodReturnValues;
	final boolean canGetMonitorFrameInfo;
	final boolean canGetMonitorInfo;
	final boolean canGetOwnedMonitorInfo;
	final boolean canGetSourceDebugExtension;
	final boolean canGetSyntheticAttribute;
	final boolean canPopFrames;
	final boolean canRedefineClasses;
	final boolean canRequestMonitorEvents;
	final boolean canRequestVMDeathEvent;
	final boolean canUnrestrictedlyRedefineClasses;
	final boolean canUseInstanceFilters;
	final boolean canUseSourceNameFilters;
	final boolean canWatchFieldAccess;
	final boolean canWatchFieldModification;

	public VMCapabilities(boolean canAddMethod, boolean canBeModified, boolean canForceEarlyReturn,
			boolean canGetBytecodes, boolean canGetClassFileVersion, boolean canGetConstantPool,
			boolean canGetCurrentContendedMonitor, boolean canGetInstanceInfo, boolean canGetMethodReturnValues,
			boolean canGetMonitorFrameInfo, boolean canGetMonitorInfo, boolean canGetOwnedMonitorInfo,
			boolean canGetSourceDebugExtension, boolean canGetSyntheticAttribute, boolean canPopFrames,
			boolean canRedefineClasses, boolean canRequestMonitorEvents, boolean canRequestVMDeathEvent,
			boolean canUnrestrictedlyRedefineClasses, boolean canUseInstanceFilters, boolean canUseSourceNameFilters,
			boolean canWatchFieldAccess, boolean canWatchFieldModification) {

		this.canAddMethod = canAddMethod;
		this.canBeModified = canBeModified;
		this.canForceEarlyReturn = canForceEarlyReturn;
		this.canGetBytecodes = canGetBytecodes;
		this.canGetClassFileVersion = canGetClassFileVersion;
		this.canGetConstantPool = canGetConstantPool;
		this.canGetCurrentContendedMonitor = canGetCurrentContendedMonitor;
		this.canGetInstanceInfo = canGetInstanceInfo;
		this.canGetMethodReturnValues = canGetMethodReturnValues;
		this.canGetMonitorFrameInfo = canGetMonitorFrameInfo;
		this.canGetMonitorInfo = canGetMonitorInfo;
		this.canGetOwnedMonitorInfo = canGetOwnedMonitorInfo;
		this.canGetSourceDebugExtension = canGetSourceDebugExtension;
		this.canGetSyntheticAttribute = canGetSyntheticAttribute;
		this.canPopFrames = canPopFrames;
		this.canRedefineClasses = canRedefineClasses;
		this.canRequestMonitorEvents = canRequestMonitorEvents;
		this.canRequestVMDeathEvent = canRequestVMDeathEvent;
		this.canUnrestrictedlyRedefineClasses = canUnrestrictedlyRedefineClasses;
		this.canUseInstanceFilters = canUseInstanceFilters;
		this.canUseSourceNameFilters = canUseSourceNameFilters;
		this.canWatchFieldAccess = canWatchFieldAccess;
		this.canWatchFieldModification = canWatchFieldModification;
	}

	@Override
	public String toString() {
		return "VMCapabilities [\n"
				+ "canAddMethod=" + canAddMethod + ",\n"
						+ "canBeModified=" + canBeModified
				+ ",\n"
				+ "canForceEarlyReturn=" + canForceEarlyReturn + ",\n"
						+ "canGetBytecodes=" + canGetBytecodes
				+ ",\n"
				+ "canGetClassFileVersion=" + canGetClassFileVersion + ",\n"
						+ "canGetConstantPool=" + canGetConstantPool
				+ ",\n"
				+ "canGetCurrentContendedMonitor=" + canGetCurrentContendedMonitor + ",\n"
						+ "canGetInstanceInfo="
				+ canGetInstanceInfo + ",\n"
						+ "canGetMethodReturnValues=" + canGetMethodReturnValues
				+ ",\n"
				+ "canGetMonitorFrameInfo=" + canGetMonitorFrameInfo + ",\n"
						+ "canGetMonitorInfo=" + canGetMonitorInfo
				+ ",\n"
				+ "canGetOwnedMonitorInfo=" + canGetOwnedMonitorInfo + ",\n"
						+ "canGetSourceDebugExtension="
				+ canGetSourceDebugExtension + ",\n"
						+ "canGetSyntheticAttribute=" + canGetSyntheticAttribute
				+ ",\n"
				+ "canPopFrames=" + canPopFrames + ",\n"
						+ "canRedefineClasses=" + canRedefineClasses
				+ ",\n"
				+ "canRequestMonitorEvents=" + canRequestMonitorEvents + ",\n"
						+ "canRequestVMDeathEvent="
				+ canRequestVMDeathEvent + ",\n"
						+ "canUnrestrictedlyRedefineClasses=" + canUnrestrictedlyRedefineClasses
				+ ",\n"
				+ "canUseInstanceFilters=" + canUseInstanceFilters + ",\n"
						+ "canUseSourceNameFilters="
				+ canUseSourceNameFilters + ",\n"
						+ "canWatchFieldAccess=" + canWatchFieldAccess
				+ ",\n"
				+ "canWatchFieldModification=" + canWatchFieldModification + "\n]";
	}

	
}
