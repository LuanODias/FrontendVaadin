package com.informabr.application.views.powerbi;

import com.informabr.application.views.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("PowerBI")
@Route(value = "PowerBI", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class PowerBIView extends Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;

    public PowerBIView() {
        constructUI();


        imageContainer.add(new PowerBIViewCard("Eleitorado",
                "https://i.postimg.cc/yNP55GTG/Eleitores.jpg", "Eleitorado", "Gráficos que ajudam o entendimento do eleitorado brasileiro", "window.open('https://app.powerbi.com/view?r=eyJrIjoiYTdmYTFlNWUtNTE0Ni00YjI3LTk0ZjYtYmQ5MGVmOGM2MzAzIiwidCI6IjJjZjdkNGQ1LWJkMWItNDk1Ni1hY2Y4LTI5OTUzOTliMjE2OCJ9');"));
        imageContainer.add(new PowerBIViewCard("Partidos",
                "https://i.postimg.cc/7LYXCZgB/Partidos.jpg","Partidos", "Gráficos que ajudam o entendimento dos partidos", "window.open('https://app.powerbi.com/view?r=eyJrIjoiYmVkNGEzNDYtOWZjYy00YWMyLTlkYWItMjU3NzgwYmNlNWY5IiwidCI6IjJjZjdkNGQ1LWJkMWItNDk1Ni1hY2Y4LTI5OTUzOTliMjE2OCJ9');"));
        imageContainer.add(new PowerBIViewCard("Candidatos",
                "https://i.postimg.cc/8zGZSHsg/Candidatos.jpg","Candidatos", "Gráficos que ajudam o entendimento dos candidatos", "window.open('https://app.powerbi.com/view?r=eyJrIjoiNjEyYTNhZjgtNjRmOC00MzJlLTg0MDUtNjcwOGRiZDgxODBmIiwidCI6IjJjZjdkNGQ1LWJkMWItNDk1Ni1hY2Y4LTI5OTUzOTliMjE2OCJ9');"));
        imageContainer.add(new PowerBIViewCard("Documentação Swagger",
                "https://i.postimg.cc/K85JvMgk/sw-agger.png", "Documentação Swagger", "Caso possua alguma dúvida em relação ao uso do Swagger, obtenha ajuda aqui", "window.open('https://swagger.io/docs/specification/about/');"));
    }

    private void constructUI() {
        addClassNames("power-bi-view", "max-w-screen-lg", "mx-auto", "pb-l", "px-l");

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames("items-center", "justify-between");

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("Links");
        header.addClassNames("mb-0", "mt-xl", "text-3xl");
        Paragraph description = new Paragraph("Royalty free photos and pictures, courtesy of Unsplash");
        description.addClassNames("mb-xl", "mt-0", "text-secondary");
        headerContainer.add(header, description);

        imageContainer = new OrderedList();
        imageContainer.addClassNames("gap-m", "grid", "list-none", "m-0", "p-0");

        container.add(header);
        add(container, imageContainer);

    }
}