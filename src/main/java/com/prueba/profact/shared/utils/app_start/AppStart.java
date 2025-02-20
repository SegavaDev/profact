package com.prueba.profact.shared.utils.app_start;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AppStart implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
		final String port = event.getApplicationContext().getEnvironment().getProperty("server.port");
		final String host = event.getApplicationContext().getEnvironment().getProperty("server.custom-host",
				"localhost");
		final String path = event.getApplicationContext().getEnvironment().getProperty("server.servlet.context-path",
				"");
		final String protocolo = Boolean.parseBoolean(
				event.getApplicationContext().getEnvironment().getProperty("server.ssl.enabled", "false")) ? "https"
						: "http";

		String url = "\033[0;34m"+ protocolo +"://" + host + ":" + port + path;
		System.out.println("\n\033[0;34m@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.print("@                                                                      @");
		System.out.print("\n@\t");
		System.out.println("\033[1mServidor corriendo en: " + url + "                   @");
		System.out.println("@                                                                      @");
		System.out.println("\033[0;34m@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
	}

}
