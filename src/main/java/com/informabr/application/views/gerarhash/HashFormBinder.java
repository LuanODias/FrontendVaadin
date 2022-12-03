package com.informabr.application.views.gerarhash;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import org.apache.catalina.User;

public class HashFormBinder {

    private GerarHashForm gerarHashForm;


    private boolean enablePasswordValidation;

    public HashFormBinder(GerarHashForm gerarHashForm) {
        this.gerarHashForm = gerarHashForm;
    }

    //Método que adiciona as lógicas de validação do formulário

    public void addBindingAndValidation(){
        BeanValidationBinder<UserDetails> binder = new BeanValidationBinder<>(UserDetails.class);
        binder.bindInstanceFields(gerarHashForm);


        //validação customizada para campos de senha
        binder.forField(gerarHashForm.getSenha()).withValidator(this::passwordValidator).bind("senha");

        //O segundo campo de senha não é conectado ao Binder, mas queremos que o binder valide caso o campo seja alterado
        gerarHashForm.getConfirmarsenha().addValueChangeListener(e -> {
            enablePasswordValidation = true;

            binder.validate();
        });

        binder.setStatusLabel(gerarHashForm.getErrorMessageField());

        gerarHashForm.getSave().addClickListener(event -> {
            try{
                //Bean vazio para adicionar os dados dentro
                UserDetails userBean = new UserDetails();

                binder.writeBean(userBean);


                //Acionar o BACKEND aqui

                showSuccess(userBean);
            } catch (ValidationException exception){
                //Não é necessário mais mensagens de erro, pois já aparecem nos campos, porém, se necessário,
                //Adicionamos erros aqui
            }

        });

    }

    private ValidationResult passwordValidator(String pass1, ValueContext ctx){

        if(pass1 == null || pass1.length() <8){
            return ValidationResult.error("Senha deve ter mais que 8 caracteres");
        }

        if (!enablePasswordValidation) {
            // usuário não preencheu o campo confirmar senha ainda, mas quando preencher irá validar.
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }

        String pass2 = gerarHashForm.getConfirmarsenha().getValue();

        if (pass1 != null && pass1.equals(pass2)) {
            return ValidationResult.ok();
        }

        return ValidationResult.error("Senhas não batem");
    }

    private void showSuccess(UserDetails userBean) {
        Notification notification =
                Notification.show("Hash cadastrado " + userBean.getNome() + ", por favor, salve ele para futuras consultas!");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        // Here you'd typically redirect the user to another view
    }
}
