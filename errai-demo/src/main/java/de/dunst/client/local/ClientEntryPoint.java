package de.dunst.client.local;

import com.google.gwt.user.client.ui.RootPanel;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by Holger on 16.08.2014.
 */
@EntryPoint
public class ClientEntryPoint {
    @Inject
    Logger logger;
    private RootPanel rootPanel;

    @PostConstruct
    public void init() {
        logger.info("ClientEntryPoint::init enter");
        logger.info("ClientEntryPoint::init left");
    }
}
