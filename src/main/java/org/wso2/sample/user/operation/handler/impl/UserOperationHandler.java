package org.wso2.sample.user.operation.handler.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.core.AbstractIdentityUserOperationEventListener;
import org.wso2.carbon.user.core.UserStoreException;
import org.wso2.carbon.user.core.UserStoreManager;

import java.util.Map;

public class UserOperationHandler extends AbstractIdentityUserOperationEventListener {

    private static Log log = LogFactory.getLog(UserOperationHandler.class);
    private static String askPasswordClaimURI = "http://wso2.org/claims/identity/askPassword";
    private static String verifyEmailClaimURI = "http://wso2.org/claims/identity/verifyEmail";

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
        claims.put(verifyEmailClaimURI, "true");
        return super.doPreAddUser(userName, credential, roleList, claims, profile, userStoreManager);
    }
}
