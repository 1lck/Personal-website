declare module "matter-js" {
	export * from "matter-js";
}

// Global type declarations for Matter.js
declare global {
	interface Window {
		engine: unknown;
		wall2: unknown;
		cornea: string;
		polar: number[];
	}
}

export type {};
