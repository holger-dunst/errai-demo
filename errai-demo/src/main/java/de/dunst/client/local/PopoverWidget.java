package de.dunst.client.local;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;

/**
 * Implementation of a Bootstrap popover.
 * <p/>
 * Created by Holger Dunst on 11.08.2014.
 */
@Templated("PopoverWidget.html")
@Dependent
public class PopoverWidget extends Composite {

    /**
     * This is the widget that contains the user-supplied content we show in the popover.
     */
    @DataField
    private DivElement popoverContent = Document.get().createDivElement();

    /**
     * Positions the popover so that its arrow points at the centre of the given widget Makes the popover visible
     *
     * @param positionNear
     */
    public void show(Widget positionNear) {
        getElement().getStyle().setDisplay(Style.Display.BLOCK);
        final int totalRight = RootPanel.get().getAbsoluteLeft() + RootPanel.get().getOffsetWidth();
        if (positionNear.getAbsoluteLeft() + positionNear.getOffsetWidth() + popoverContent.getOffsetWidth() < totalRight) {
            getElement().getStyle().setLeft(positionNear.getAbsoluteLeft() + positionNear.getOffsetWidth(), Style.Unit.PX);
        } else {
            getElement().getStyle().setLeft(positionNear.getAbsoluteLeft() - (30 + popoverContent.getOffsetWidth()), Style.Unit.PX);
        }
        getElement().getStyle().setTop(
                positionNear.getAbsoluteTop() + positionNear.getOffsetHeight() / 2
                        - getElement().getOffsetHeight() / 2, Style.Unit.PX);

    }

    /**
     * Causes this popover to become invisible.
     */
    public void hide() {
        getElement().getStyle().setDisplay(Style.Display.NONE);
    }

    /**
     * Sets the content of the InfoWidget.
     */
    public void setInnerHTML(String html) {
        popoverContent.setInnerHTML(html);
    }

    /**
     * Adds this popover to the document so it can be made visible. This method is called automatically when this bean is
     * created.
     */
    @PostConstruct
    private void init() {
        //this.addStyleName("popover-content");
        RootPanel.get().add(this);
    }

    /**
     * Removes this popover from the document so it does not leak resources. This method is called automatically when this bean
     * is destroyed.
     */
    @PreDestroy
    private void destroy() {
        RootPanel.get().remove(this);
    }
}
