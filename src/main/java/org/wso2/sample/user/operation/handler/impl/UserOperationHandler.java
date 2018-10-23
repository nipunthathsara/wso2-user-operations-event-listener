package org.wso2.sample.user.operation.handler.impl;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.core.AbstractIdentityUserOperationEventListener;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.UserStoreManager;

import java.util.Map;

public class UserOperationHandler extends AbstractIdentityUserOperationEventListener {

    private static Log log = LogFactory.getLog(UserOperationHandler.class);
    private static final String askPasswordClaimURI = "http://wso2.org/claims/identity/askPassword";

    /**
     * This should be executed before org.wso2.carbon.identity.governance.listener.IdentityMgtEventListener.
     * Hence the order ID is set to 94.
     *
     * @return
     */
    @Override
    public int getExecutionOrderId() {
        return 94;
    }

    @Override
    public boolean doPreAddUser(String userName, Object credential, String[] roleList, Map<String, String> claims,
                                String profile, UserStoreManager userStoreManager) throws UserStoreException {
        log.debug("in pre add user");
        claims.put(askPasswordClaimURI, "true");
        boolean result = super.doPreAddUser(userName, credential, roleList, claims, profile, userStoreManager);
        if (log.isDebugEnabled()) {
            log.debug("Logging user info Pre Add User");
            logUserInfo(userName, claims);
        }
        return result;

    }

    public boolean doPostAddUser
            (String userName, Object credential, String[] roleList, Map<String, String> claims,
             String profile, UserStoreManager userStoreManager) throws UserStoreException {

        if (log.isDebugEnabled()) {
            log.debug("Logging user info Post Add User");
            logUserInfo(userName, claims);
        }
        return true;
    }

    private void logUserInfo(String username, Map<String, String> claims) {

        try {
            Gson gson = new Gson();
            log.debug("User creating : " + username + ": claims: " + gson.toJson(claims));
        } catch (Throwable e) {
            log.error("Error while logging info.", e);
        }
    }
}
