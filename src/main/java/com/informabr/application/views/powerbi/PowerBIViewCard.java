package com.informabr.application.views.powerbi;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;

public class PowerBIViewCard extends ListItem {

    public PowerBIViewCard(String text, String url,String title, String paragraph, String link) {
        addClassNames("bg-contrast-5", "flex", "flex-col", "items-start", "p-m", "rounded-l");

        Div div = new Div();
        div.addClassNames("bg-contrast", "flex items-center", "justify-center", "mb-m", "overflow-hidden",
                "rounded-m w-full");
        div.setHeight("160px");

        Image image = new Image();
        image.setWidth("100%");
        image.setSrc(url);
        image.setAlt(text);

        div.add(image);

        Span header = new Span();
        header.addClassNames("text-xl", "font-semibold");
        header.setText(title);


        Paragraph description = new Paragraph(paragraph);
        description.addClassName("my-m");

        Button button = new Button();
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.setText("Acessar");
        button.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().executeJavaScript(link);
        });

        add(div, header, description, button);

    }



}
