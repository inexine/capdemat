package fr.cg95.cvq.service.request;

import fr.cg95.cvq.business.request.Request;
import fr.cg95.cvq.business.request.RequestLock;
import fr.cg95.cvq.exception.CvqException;
import fr.cg95.cvq.exception.CvqObjectNotFoundException;
import fr.cg95.cvq.service.request.annotation.IsRequest;

public interface IRequestLockService {

    /**
     * Get a request by id, after locking it.
     */
    Request getAndLock(@IsRequest final Long id)
        throws CvqException, CvqObjectNotFoundException;

    /**
     * Get a request by id, after trying to lock it.
     */
    Request getAndTryToLock(@IsRequest final Long id)
        throws CvqException, CvqObjectNotFoundException;

    /**
     * Get the lock put on this request if it exists
     */
    RequestLock getRequestLock(@IsRequest final Long requestId);

    /**
     * Put a lock on a request
     */
    void lock(@IsRequest final Long requestId)
        throws CvqException;

    /**
     * Check if this request is locked by another person than current user.
     *
     * @param requestId the ID of the request to check
     * @return true if the request is locked by another one,
     *         false otherwise (no lock, or lock owned by current user)
     */
    boolean isLocked(@IsRequest final Long requestId);

    /**
     * Check if this request is locked by current user.
     *
     * @param requestId the ID of the request to check
     * @return true if there is a lock on this request and
     *         it is owned by current user
     */
    boolean isLockedByCurrentUser(@IsRequest final Long requestId);

    /**
     * Drop the lock on this request if current user has it.
     *
     * @param requestId the ID of the request to release
     */
    void release(@IsRequest final Long requestId);

    /**
     * Clean obsolete request locks
     */
    void cleanRequestLocks();
}
