/**************************************************************************************************
 * File: CustomIdentityStoreJPAHelper.java
 * Course materials (20W) CST 8277
 * @author Mike Norman
 * 
 * Group Members:
 * Sam Heaton
 * Michael Norris
 * Josh Diabo
 * Daria Ponomareva
 *
 */
package com.algonquincollege.cst8277.security;

import static com.algonquincollege.cst8277.utils.MyConstants.PU_NAME;
import static java.util.Collections.emptySet;

import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.TypedQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.algonquincollege.cst8277.models.SecurityRole;
import com.algonquincollege.cst8277.models.SecurityUser;

@Singleton
public class CustomIdentityStoreJPAHelper {

    @PersistenceContext(name=PU_NAME)
    protected EntityManager em;

    @Inject
    protected Pbkdf2PasswordHash pbAndjPasswordHash;

    public SecurityUser findUserByName(String username) {
        SecurityUser user = null;
        try {
            user = null;
            TypedQuery<SecurityUser> q = em.createQuery("select secureUser from SecurityUser secureUser where secureUser.username = :param1",
                SecurityUser.class).setParameter("param1", username);
            user = q.getSingleResult();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Set<String> findRoleNamesForUser(String username) {
        Set<String> rolenames = emptySet();
        TypedQuery<SecurityRole> a = em.createQuery("select e from SecurityUser e.roles where e.user = :param2",
            SecurityRole.class);
      
        a.setParameter("param2", username);
        rolenames = a.getResultList().stream().map( s -> s.getRoleName()).collect(Collectors.toSet());
        return rolenames;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveSecurityUser(SecurityUser user) {
        em.persist(user);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveSecurityRole(SecurityRole role) {
        em.persist(role);
    }
}