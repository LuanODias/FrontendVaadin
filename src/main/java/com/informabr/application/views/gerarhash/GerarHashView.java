package com.informabr.application.views.gerarhash;

import com.informabr.application.data.entity.SamplePerson;
import com.informabr.application.data.service.SamplePersonService;
import com.informabr.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@PageTitle("Gerar Hash")
@Route(value = "gerar-hash", layout = MainLayout.class)
@Uses(Icon.class)
public class GerarHashView extends VerticalLayout {





    public GerarHashView() {

        GerarHashForm gerarHashForm = new GerarHashForm();
        setSizeFull();
        setHorizontalComponentAlignment(Alignment.CENTER, gerarHashForm);
        setAlignItems(Alignment.CENTER);
        addClassName("gerar-hash-view");

        add(createTitle());
        add(gerarHashForm);

        gerarHashForm.getSave().addClickListener(event -> {



        });

        HashFormBinder binder = new HashFormBinder(gerarHashForm);
        binder.addBindingAndValidation();


    }

    private Component createTitle() {
        return new H3("Cadastre uma chave para acessar os endpoints");
    }





}
