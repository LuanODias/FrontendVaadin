package com.informabr.application.views.gerarhash;

import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;

import java.util.concurrent.atomic.AtomicBoolean;

public class HashFormBinder {

    private GerarHashForm gerarHashForm;
    private Boolean status;


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


                    Termos(userBean);



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

    public void Termos(UserDetails userBean){

        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader("Termos de consentimento");
        dialog.setText(
                "O presente \"Termo de Consentimento\" visa registrar a manifestação livre e\n" +
                        "inequívoca pela qual o Titular concorda com o tratamento dos seus dados\n" +
                        "pessoais para a finalidade específica, em conformidade com a Lei nº 13.709 – Lei\n" +
                        "Geral de Proteção de Dados Pessoais (LGPD)\n" +
                        "Ao manifestar a sua aceitação ao presente termo, o Titular consente e concorda\n" +
                        "que a InformaBR inscrita no CNPJ 70.908.025/0001-73 com sede na SC-401, 3730 - Saco Grande, Florianópolis - SC,\n" +
                        "doravante denominada Controladora, tome decisões referentes ao tratamento\n" +
                        "dos seus dados pessoais abaixo descritos, necessários à prestação dos serviços\n" +
                        "ofertados envolvendo operações como as que se referem à coleta, ao processamento e armazenamento.\n" +
                        "Para fins deste Termo de Consentimento, aplicam-se também as seguintes\n" +
                        "definições:\n" +
                        "• Sites: qualquer página da web sob o domínio da Controladora;\n" +
                        "• Cookies: arquivos enviados pelo servidor do site para o computador do\n" +
                        "Usuário, com a finalidade de identificar o computador e obter dados de\n" +
                        "acesso, como páginas navegadas ou links clicados, permitindo, desta forma,\n" +
                        "personalizar a utilização do site, de acordo com o seu perfil;\n" +
                        "• IP: abreviatura de Internet Protocol. É um conjunto de números que identifica\n" +
                        "o computador do Usuário na Internet;\n" +
                        "• Logs: registros de atividades do Usuário efetuadas no site;\n" +
                        "• Session ID: identificação da sessão do Usuário no processo de inscrição ou\n" +
                        "quando utilizado de alguma forma o site;\n" +
                        "• Usuário: todo aquele que passar a usar o site;\n" +
                        "• Sistemas: sites e/ou programas de computador que a Controladora utiliza para\n" +
                        "realizar seus processos seletivos e de matrícula.\n" +
                        "Obtenção dos dados e informações\n" +
                        "Os dados e as informações serão obtidos quando o Usuário:\n" +
                        "1. Passar a utilizar o sistema\n" +
                        "2. Fazer o cadastro e logar no sistema\n" +
                        "Armazenamento dos Dados e das Informações: todos os dados e todas as\n" +
                        "informações coletados dos Usuários serão incorporados ao banco de dados da\n" +
                        "Controladora, sendo esta sua responsável e proprietária. Os dados e as\n" +
                        "informações coletados estarão armazenados em ambiente seguro, observado o\n" +
                        "estado da técnica disponível, e somente poderão ser acessados por pessoas\n" +
                        "qualificadas e autorizadas pela Controladora. Além disso, a Controladora afirma\n" +
                        "que não compartilhará, venderá ou apresentará os dados dos Usuários para\n" +
                        "terceiros que não sejam seus parceiros diretamente envolvidos em seus\n" +
                        "processos com as finalidades neste termo apresentadas.\n" +
                        "O Usuário é o proprietário dos dados e está apto a adicionar, excluir ou modificar\n" +
                        "quaisquer informações que estiverem ligadas ao seu perfil de usuário no sistema\n" +
                        "da Controladora. Por isso, o Usuário declara estar ciente e concorda com a\n" +
                        "coleta, o armazenamento, tratamento, processamento e uso das Informações\n" +
                        "enviadas e/ou transmitidas pelo Usuário nos termos estabelecidos neste Termo\n" +
                        "de Consentimento.\n" +
                        "Finalidades do Tratamento dos Dados\n" +
                        "O tratamento dos dados pessoais listados neste termo possui as seguintes\n" +
                        "finalidades: fornecer acesso à área restrita do site ou às suas funcionalidades\n" +
                        "exclusivas.\n" +
                        "\n" +
                        "\n" +
                        "Do Registro de Atividades: a Controladora poderá registrar as atividades\n" +
                        "efetuadas pelo Usuário no site, por meio da Session ID do Usuário. \n" +
                        "Os registros mencionados poderão ser utilizados pela Controladora em\n" +
                        "casos de investigação de fraudes ou de alterações indevidas em seus sistemas e\n" +
                        "cadastros.\n" +
                        "Dados Pessoais\n" +
                        "A Controladora fica autorizada a tomar decisões referentes ao tratamento e a\n" +
                        "realizar o tratamento dos seguintes dados pessoais do Titular: nome completo, email, cpf e senha para acesso ao site institucional da\n" +
                        "Controladora.\n" +
                        "Compartilhamento de Dados\n" +
                        "A Controladora fica autorizada a compartilhar os dados pessoais do Titular\n" +
                        "com outros agentes de tratamento de dados, caso seja necessário para as\n" +
                        "finalidades listadas neste termo, observados os princípios e as garantias\n" +
                        "estabelecidas pela Lei nº 13.709.\n" +
                        "Segurança dos Dados\n" +
                        "A Controladora responsabiliza-se pela manutenção de medidas de\n" +
                        "segurança, técnicas e administrativas aptas a proteger os dados pessoais\n" +
                        "de acessos não autorizados e de situações acidentais ou ilícitas de\n" +
                        "destruição, perda, alteração, comunicação ou qualquer forma de\n" +
                        "tratamento inadequado ou ilícito.\n" +
                        "Em conformidade ao art. 48 da Lei nº 13.709, o Controlador comunicará ao\n" +
                        "Titular e à Autoridade Nacional de Proteção de Dados (ANPD) a ocorrência\n" +
                        "de incidente de segurança que possa acarretar risco ou dano relevante ao\n" +
                        "Titular.\n" +
                        "Término do Tratamento dos Dados\n" +
                        "A Controladora poderá manter e tratar os dados pessoais do Titular durante todo\n" +
                        "o período em que os mesmos forem pertinentes ao alcance das finalidades\n" +
                        "listadas neste termo. Dados pessoais anonimizados, sem possibilidade de\n" +
                        "associação ao indivíduo, poderão ser mantidos por período indefinido.\n" +
                        "O Titular poderá solicitar via e-mail ou correspondência à Controladora, a\n" +
                        "qualquer momento, que sejam eliminados os dados pessoais não anonimizados\n" +
                        "do Titular. O Titular fica ciente de que poderá ser inviável à Controladora\n" +
                        "continuar o fornecimento de produtos ou serviços ao Titular a partir da\n" +
                        "eliminação dos dados pessoais.\n" +
                        "Direitos do Titular\n" +
                        "O Titular tem direito a obter da Controladora, em relação aos dados por ele\n" +
                        "tratados, a qualquer momento e mediante requisição: I – confirmação da\n" +
                        "existência de tratamento; II – acesso aos dados; III – correção de dados\n" +
                        "incompletos, inexatos ou desatualizados; IV – anonimização, bloqueio ou\n" +
                        "eliminação de dados desnecessários, excessivos ou tratados em\n" +
                        "desconformidade com o disposto na Lei nº 13.709; V – portabilidade dos dados a\n" +
                        "outro fornecedor de serviço ou produto, mediante requisição expressa e\n" +
                        "observados os segredos comercial e industrial, de acordo com a regulamentação\n" +
                        "do órgão controlador; VI – eliminação dos dados pessoais tratados com o\n" +
                        "consentimento do Titular, exceto nas hipóteses previstas no art. 16 da Lei nº\n" +
                        "13.709; VII – informação das entidades públicas e privadas com as quais a\n" +
                        "Controladora realizou uso compartilhado de dados; VIII – informação sobre a\n" +
                        "possibilidade de não fornecer consentimento e sobre as consequências da\n" +
                        "negativa; IX – revogação do consentimento, nos termos do § 5º do art. 8º da Lei\n" +
                        "nº 13.709.\n" +
                        "Direito de Revogação do Consentimento\n" +
                        "Este consentimento poderá ser revogado pelo usuário, a qualquer momento, por\n" +
                        "duas formas de solicitação:\n" +
                        "1. E-mail: contato@informabr.com.br\n" +
                        "2. Correspondência ao controlador: SC-401, 3730 - Saco Grande, Florianópolis - SC");


        dialog.setRejectText("Discordo");
        dialog.setRejectable(true);
        dialog.addRejectListener(rejectEvent -> {
            showError();

        });

        dialog.setCancelable(true);
        dialog.setCancelText("Cancelar");
        dialog.addCancelListener(cancelEvent -> {
           dialog.close();

        });

        dialog.setConfirmText("Concordo");
        dialog.addConfirmListener(confirmEvent -> {
            showSuccess(userBean);
        });
        dialog.open();
    }

    private void showSuccess(UserDetails userBean) {
        Notification notification =
                Notification.show("Hash cadastrado " + userBean.getNome() + ", por favor, salve ele para futuras consultas!");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        // Here you'd typically redirect the user to another view
    }

    private void showError(){
        Notification notification =
                Notification.show("Você precisa aceitar os termos para se cadastrar");
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
    }
}
