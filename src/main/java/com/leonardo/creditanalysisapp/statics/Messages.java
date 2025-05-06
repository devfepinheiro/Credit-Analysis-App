package com.leonardo.creditanalysisapp.statics;

/**
 * Contains all message templates used in the application. Using a class with
 * constants makes it easier to maintain and localize messages.
 */
public final class Messages {
    // Private constructor to prevent instantiation
    private Messages() {
        throw new AssertionError("Messages class should not be instantiated");
    }

    /* Proposal status messages */
    public static final String PENDING_PROPOSAL_NAME =
            "Prezado(a) %s, sua proposta está pendente de aprovação. Aguarde!";
    public static final String NEGATIVATED_NAME =
            "Prezado(a) %s, sua proposta não foi aprovada. Seu nome está negativado.";
    public static final String LOW_SCORE_NAME =
            "Prezado(a), %s, seu score não foi suficiente para aprovação. Seu score é baixo.";
    public static final String APPROVED_NAME = "Prezado(a) %s, sua proposta foi aprovada. Parabéns!";
    public static final String NOT_APPROVED_NAME = """
        Prezado(a) %s, sua proposta não foi aprovada.\
         Infelizmente não podemos aprovar sua proposta no momento.\
         Entre em contato conosco para mais informações.""";

    /* Error messages */
    public static final String ERROR_ANALYZING_PROPOSAL = "Erro ao analisar proposta: %s";
    public static final String INVALID_PROPOSAL = "Proposta inválida: %s";
    public static final String SYSTEM_ERROR = "Erro no sistema. Por favor, tente novamente mais tarde.";
}
