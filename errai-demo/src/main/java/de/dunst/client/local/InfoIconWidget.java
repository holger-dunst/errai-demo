package de.dunst.client.local;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 * Created by Holger Dunst on 11.08.2014.
 * <p/>
 */
@Templated("InfoIconWidget.html")
@Dependent
public class InfoIconWidget extends Composite {
    @Inject
    private InfoWidget infoWidget;

    private String content;

    public void setInnerHTML(String html) {
        this.content = html;
    }

    @PostConstruct
    private void init() {
        infoWidget.hide();
        content = getElement().getInnerText();
        getElement().setInnerText("");
        addDomHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(final MouseOverEvent event) {
                infoWidget.setInnerHTML(content);
                infoWidget.show(InfoIconWidget.this);
            }
        }, MouseOverEvent.getType());
        addDomHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(final MouseOutEvent event) {
                infoWidget.hide();
            }
        }, MouseOutEvent.getType());
    }

}
