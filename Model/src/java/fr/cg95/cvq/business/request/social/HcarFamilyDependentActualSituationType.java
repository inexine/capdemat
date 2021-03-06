package fr.cg95.cvq.business.request.social;

import fr.cg95.cvq.dao.hibernate.PersistentStringEnum;

/**
 * Generated class file, do not edit !
 */
public final class HcarFamilyDependentActualSituationType extends PersistentStringEnum {

    private static final long serialVersionUID = 1L;
  
    public static final HcarFamilyDependentActualSituationType SCHOOLING = new HcarFamilyDependentActualSituationType("Schooling");
  
    public static final HcarFamilyDependentActualSituationType LEARNING = new HcarFamilyDependentActualSituationType("Learning");
  
    public static final HcarFamilyDependentActualSituationType MEDICO_SOCIAL = new HcarFamilyDependentActualSituationType("MedicoSocial");
  

    /**
     * Prevent instantiation and subclassing with a private constructor.
     */
    private HcarFamilyDependentActualSituationType(String value) {
        super(value);
    }

    public HcarFamilyDependentActualSituationType() {}

    public static HcarFamilyDependentActualSituationType[] allHcarFamilyDependentActualSituationTypes = {
        SCHOOLING,
        LEARNING,
        MEDICO_SOCIAL
    };

    public static HcarFamilyDependentActualSituationType getDefaultHcarFamilyDependentActualSituationType() {
        return null;
    }

    public static HcarFamilyDependentActualSituationType forString(final String enumAsString) {
        for (HcarFamilyDependentActualSituationType value : allHcarFamilyDependentActualSituationTypes)
            if (value.toString().equals(enumAsString))
                return value;
        return getDefaultHcarFamilyDependentActualSituationType();
    }
}
