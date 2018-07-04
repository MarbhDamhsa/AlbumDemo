package com.the.insomniacsdream;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.the.insomniacsdream.controller.AlbumController;

@Component
public class JerseyConfig extends ResourceConfig{

	public JerseyConfig() {
		register(AlbumController.class);
	}
}
