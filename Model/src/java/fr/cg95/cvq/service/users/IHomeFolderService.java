package fr.cg95.cvq.service.users;

import java.util.List;

import fr.cg95.cvq.business.users.Address;
import fr.cg95.cvq.business.users.Adult;
import fr.cg95.cvq.business.users.Child;
import fr.cg95.cvq.business.users.HomeFolder;
import fr.cg95.cvq.business.users.Individual;
import fr.cg95.cvq.business.users.RoleType;
import fr.cg95.cvq.exception.CvqException;
import fr.cg95.cvq.exception.CvqModelException;
import fr.cg95.cvq.exception.CvqObjectNotFoundException;
import fr.cg95.cvq.security.annotation.IsHomeFolder;
import fr.cg95.cvq.security.annotation.IsIndividual;

/**
 * Service related to the management of home folders.
 *
 * @author bor@zenexity.fr
 */
public interface IHomeFolderService {

    /** the notification type to use when a password is reset via secret question/answer */
    public static enum PasswordResetNotificationType {
        /** the adult has an email address it will be sent to*/
        ADULT_EMAIL,
        /** the adult has no email address but the VO Card Request category has one */
        CATEGORY_EMAIL,
        /** none have an address, the password will be output in the success message */
        INLINE;
    }

    /**
     * Create a fresh new home folder containing only the given adult.
     * 
     * It is called upon the issuing of an out-of-account request 
     * (and a temporary account will thus be created).
     */
    HomeFolder create(Adult adult)
        throws CvqException;

    /**
     * Create a fresh new home folder from a set of adults and children.
     * 
     * It is called upon the issuing of an home folder creation request.
     */
    HomeFolder create(List<Adult> adults, List<Child> children, Address address)
        throws CvqException, CvqModelException;

    void modify(@IsHomeFolder final HomeFolder homeFolder)
        throws CvqException;
    
    void modify(@IsHomeFolder final Long homeFolderId, final Long keyId,
            final List<Adult> newAdults, List<Child> newChildren, Address adress)
        throws CvqException;
    
    void delete(@IsHomeFolder final Long id)
    	throws CvqException, CvqObjectNotFoundException;

    /**
     * Remove individual from the given home folder.
     */
    void deleteIndividual(@IsHomeFolder final Long homeFolderId, final Long individualId)
        throws CvqException, CvqObjectNotFoundException;
    
    List<HomeFolder> getAll(boolean filterArchived, boolean filterTemporary)
        throws CvqException;

    HomeFolder getById(@IsHomeFolder final Long id)
        throws CvqException, CvqObjectNotFoundException;

    List<Child> getChildren(@IsHomeFolder final Long homeFolderId)
        throws CvqException;

    List<Adult> getAdults(@IsHomeFolder final Long homeFolderId)
        throws CvqException;
    
    List<Individual> getIndividuals(@IsHomeFolder final Long homeFolderId)
        throws CvqException;
    
    /**
     * Get a list of individuals who have a role in the home folder but are not part of it.
     */
    List<Individual> getExternalIndividuals(@IsHomeFolder final Long homeFolderId)
        throws CvqException;

    // Role-related methods
    /////////////////////////////////////
    
    void addHomeFolderRole(@IsIndividual Individual owner, 
            @IsHomeFolder final Long homeFolderId, final RoleType role)
        throws CvqException;
    
    void addHomeFolderRole(@IsIndividual final Individual owner, final RoleType role)
        throws CvqException;

    void addIndividualRole(@IsIndividual Individual owner, 
            final Individual individual, final RoleType role)
        throws CvqException;

    void removeRolesOnSubject(@IsHomeFolder final Long homeFolderId, final Long individualId)
        throws CvqException;    

    boolean removeHomeFolderRole(@IsIndividual Individual owner, 
            @IsHomeFolder final Long homeFolderId, final RoleType role)
        throws CvqException;
    
    boolean removeIndividualRole(@IsIndividual Individual owner, final Individual individual, 
            final RoleType role)
        throws CvqException;
    
    /*
     * TODO : refactor role management 
     *  - unauthentified use case
     *  - authentified and transient object management (ex : new individual in a HomeFolderModification request)
     *  - verify security policy
     */
    void addRole(Individual owner, final Individual individual, final RoleType role)
        throws CvqException;
    
    void addRole(Individual owner, final Individual individual, final Long homeFolderId, 
            final RoleType role)
        throws CvqException;
    
    boolean removeRole(Individual owner, final Individual individual,  final RoleType role)
        throws CvqException;
    
    boolean removeRole(Individual owner, final Individual individual, final Long homeFolderId, 
            final RoleType role)
        throws CvqException;
    
    /**
     * Save or update all foreign (from homefolder) role owner.
     * Prepare individualRole for each foreign owner before persisting it.
     * TODO : refactor foreign owner managment
     */
    public void saveForeignRoleOwners(Long homeFolderId, List<Adult> adults, List<Child> children,
            List<Adult> foreignRoleOwners) throws CvqException, CvqModelException;
    
    /**
     * Perform the checking and finalization on the roles each of the given individual 
     * has on this home folder.
     * 
     * For roles on transient objects (home folder or individual), it will set the correct
     * identifier values.
     */
    void checkAndFinalizeRoles(@IsHomeFolder Long homeFolderId, 
            List<Adult> adults, List<Child> children)
        throws CvqException, CvqModelException;

    boolean hasHomeFolderRole(@IsIndividual final Long ownerId, 
            @IsHomeFolder final Long homeFolderId, final RoleType role)
        throws CvqException;
    
    boolean hasIndividualRole(@IsIndividual final Long ownerId, 
            final Individual individual, final RoleType role)
        throws CvqException;
    
    /**
     * Get the adult that has the 
     * {@link RoleType#HOME_FOLDER_RESPONSIBLE home folder responsible role} on the
     * given home folder.
     */
    Adult getHomeFolderResponsible(@IsHomeFolder final Long homeFolderId)
        throws CvqException;
    
    List<Individual> getByHomeFolderRole(@IsHomeFolder final Long homeFolderId, 
            RoleType role);
    
    List<Individual> listByHomeFolderRoles(@IsHomeFolder final Long homeFolderId, 
            RoleType[] roles);

    List<Individual> getBySubjectRole(@IsIndividual Long subjectId, RoleType role);

    List<Individual> getBySubjectRoles(@IsIndividual Long subjectId, RoleType[] roles);

    /**
     * Validate an home folder and its associated individuals.
     */
    void validate(@IsHomeFolder final Long id)
        throws CvqException, CvqObjectNotFoundException;

    /**
     * Invalidate an home folder and its associated individuals.
     */
    void invalidate(@IsHomeFolder final Long id)
        throws CvqException, CvqObjectNotFoundException;

    /**
     * Disable an home folder and its associated individuals and requests.
     */
    void archive(@IsHomeFolder final Long id)
        throws CvqException, CvqObjectNotFoundException;

    /**
     * Send the new password by email to the home folder's responsible,
     * or to the category address if the responsible has no email address,
     * or does nothing if none have an address.
     *
     * @return the notification type used to send the new password
     */
    PasswordResetNotificationType notifyPasswordReset(Adult adult, String password, String categoryAddress)
        throws CvqException;
}
