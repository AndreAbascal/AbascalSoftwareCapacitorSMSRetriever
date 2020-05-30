declare module "@capacitor/core" {
	interface PluginRegistry {
		CapacitorSMSRetriever: CapacitorSMSRetrieverPlugin;
	}
}

export interface CapacitorSMSRetrieverPlugin {
	start(): Promise<any>;
}
