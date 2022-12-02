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
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
public class GerarHashView extends Div {

    @NotBlank
    private TextField nome = new TextField("Nome");

    @NotBlank
    @Email
    private EmailField email = new EmailField("Endereço de E-mail");
    @NotBlank
    @CPF
    private TextField cpf = new TextField("CPF");
    @NotBlank
    @Size(min = 8, max = 24, message = "A senha deve possuir entre 8 e 24 caracteres")
    private PasswordField senha = new PasswordField("Senha");

    @NotBlank
    private PasswordField confirmarsenha = new PasswordField("Confirmar senha");

    private Button cancel = new Button("Cancelar");
    private Button save = new Button("Salvar");

    private Binder<SamplePerson> binder = new Binder<>(SamplePerson.class);

    public GerarHashView(SamplePersonService personService) {
        addClassName("gerar-hash-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());

        save.addClickListener(e -> {
            personService.update(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new SamplePerson());
    }

    private Component createTitle() {
        return new H3("Personal information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(nome, email, cpf, senha, confirmarsenha);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }




}
