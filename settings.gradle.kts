pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
	}
	resolutionStrategy {
		eachPlugin {
			if (requested.id.namespace == "com.google.cloud.tools") {
				useModule("com.google.cloud.tools:appengine-gradle-plugin:${requested.version}")
			}
		}
	}
}
rootProject.name = "gcp-speedtest-api"
