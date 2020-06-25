import {WebPlugin} from "@capacitor/core";

declare module "@capacitor/core/dist/esm/core-plugin-definitions" {
	interface PluginRegistry {
		CapacitorSMSRetriever: CapacitorSMSRetrieverPlugin;
	}
}

export interface CapacitorSMSRetrieverPlugin extends WebPlugin{
	start(): Promise<any>;
	stop(): Promise<any>;
}
