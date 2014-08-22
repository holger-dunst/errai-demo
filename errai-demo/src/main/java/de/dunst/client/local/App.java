package de.dunst.client.local;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import de.dunst.client.shared.HelloMessage;
import de.dunst.client.shared.Response;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.slf4j.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * Created by Holger on 09.08.2014.
 */
@Page(role = DefaultPage.class)
@Templated("App.html#root")
public class App extends Composite {
    @Inject
    private Logger logger;

    @Inject
    private Event<HelloMessage> messageEvent;

    @Inject
    @DataField
    private Button send;
    @Inject
    @DataField
    private TextBox message;

    @Inject
    @DataField
    private Label responseLabel;

    @EventHandler("send")
    private void onClickSend(ClickEvent e) {
        logger.info("Handling click event!");
        fireMessage();
    }

    /**
     * Fires a CDI HelloMessage with the current contents of the message textbox.
     */
    void fireMessage() {
        final String text = message.getText();
        HelloMessage event = new HelloMessage(text);
        messageEvent.fire(event);
    }

    public void response(@Observes Response event) {
        logger.info("Got a Response!");
        responseLabel.setText("HelloMessage from Server: " + event.getMessage().toUpperCase());
        responseLabel.setVisible(true);
    }
}
