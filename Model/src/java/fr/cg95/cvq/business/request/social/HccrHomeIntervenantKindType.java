package fr.cg95.cvq.business.request.social;

import fr.cg95.cvq.dao.hibernate.PersistentStringEnum;

/**
 * Generated class file, do not edit !
 */
public final class HccrHomeIntervenantKindType extends PersistentStringEnum {

    private static final long serialVersionUID = 1L;
  
    public static final HccrHomeIntervenantKindType CARER = new HccrHomeIntervenantKindType("Carer");
  
    public static final HccrHomeIntervenantKindType HOME_HELP = new HccrHomeIntervenantKindType("HomeHelp");
  
    public static final HccrHomeIntervenantKindType OTHER = new HccrHomeIntervenantKindType("Other");
  

    /**
     * Prevent instantiation and subclassing with a private constructor.
     */
    private HccrHomeIntervenantKindType(String value) {
        super(value);
    }

    public HccrHomeIntervenantKindType() {}

    public static HccrHomeIntervenantKindType[] allHccrHomeIntervenantKindTypes = {
        CARER,
        HOME_HELP,
        OTHER
    };

    public static HccrHomeIntervenantKindType getDefaultHccrHomeIntervenantKindType() {
        return null;
    }

    public static HccrHomeIntervenantKindType forString(final String enumAsString) {
        for (HccrHomeIntervenantKindType value : allHccrHomeIntervenantKindTypes)
            if (value.toString().equals(enumAsString))
                return value;
        return getDefaultHccrHomeIntervenantKindType();
    }
}
