package com.jaunenoir.shared;

public final class Constants {

    private Constants() {}

    // API
    public static final String API_PREFIX = "/api/v1";

    // Pagination
    public static final int PAGE_SIZE_DEFAULT = 20;
    public static final int PAGE_SIZE_MAX = 100;

    // Notation
    public static final int NOTE_MIN = 1;
    public static final int NOTE_MAX = 5;

    // Messages erreurs
    public static final String UTILISATEUR_NON_TROUVE = "Utilisateur introuvable";
    public static final String COURSE_NON_TROUVEE = "Course introuvable";
    public static final String VEHICULE_NON_TROUVE = "Véhicule introuvable";
    public static final String NOTATION_NON_TROUVEE = "Notation introuvable";
    public static final String TICKET_NON_TROUVE = "Ticket de support introuvable";
    public static final String TELEPHONE_DEJA_UTILISE = "Ce numéro de téléphone est déjà utilisé";
    public static final String EMAIL_DEJA_UTILISE = "Cet email est déjà utilisé";
    public static final String IMMATRICULATION_DEJA_UTILISEE = "Cette immatriculation est déjà enregistrée";
    public static final String NOTATION_DEJA_SOUMISE = "Vous avez déjà noté cette course";
}
