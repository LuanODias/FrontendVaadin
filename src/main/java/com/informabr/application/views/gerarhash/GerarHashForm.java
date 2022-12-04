package com.informabr.application.views.gerarhash;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class GerarHashForm extends FormLayout {

    private TextField nome;

    private EmailField email;

    private TextField cpf;

    private PasswordField senha;

    private PasswordField confirmarsenha;

    private Span errorMessageField;

    private Button cancel;
    private Button save;


    public GerarHashForm(){
        nome = new TextField("Nome");
        nome.setRequiredIndicatorVisible(true);

        email = new EmailField("E-mail");
        email.setRequiredIndicatorVisible(true);

        cpf = new TextField("Cpf");
        cpf.setRequiredIndicatorVisible(true);

        senha = new PasswordField("Senha");
        senha.setRequiredIndicatorVisible(true);

        confirmarsenha = new PasswordField("Confirmar senha");
        confirmarsenha.setRequiredIndicatorVisible(true);

        errorMessageField = new Span();

        cancel = new Button("Limpar");

        save = new Button("Cadastrar",
                new Icon(VaadinIcon.KEY));
        save.setAutofocus(true);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(nome, email, cpf, email, senha,
                confirmarsenha, errorMessageField,save ,cancel);

        setMaxWidth("500px");

        setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));

        // These components always take full width
        setColspan(email, 2);
        setColspan(errorMessageField, 2);
        setColspan(cancel, 2);
        setColspan(save, 2);
    }


    public Button getSave() {
        return save;
    }

    public void setSave(Button save) {
        this.save = save;
    }

    public TextField getNome() {
        return nome;
    }

    public void setNome(TextField nome) {
        this.nome = nome;
    }

    public EmailField getEmail() {
        return email;
    }

    public void setEmail(EmailField email) {
        this.email = email;
    }

    public TextField getCpf() {
        return cpf;
    }

    public void setCpf(TextField cpf) {
        this.cpf = cpf;
    }

    public PasswordField getSenha() {
        return senha;
    }

    public void setSenha(PasswordField senha) {
        this.senha = senha;
    }

    public PasswordField getConfirmarsenha() {
        return confirmarsenha;
    }

    public void setConfirmarsenha(PasswordField confirmarsenha) {
        this.confirmarsenha = confirmarsenha;
    }

    public Span getErrorMessageField() {
        return errorMessageField;
    }

    public void setErrorMessageField(Span errorMessageField) {
        this.errorMessageField = errorMessageField;
    }
}
