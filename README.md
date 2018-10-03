# wso2-user-operations-event-listener
user operation event listener to enable ask password for any user onboarding via SCIM2

Steps
1. Set SMTP server details in the <IS_HOME>/repository/conf/output-eventadapters.xml file.
2. Checkout and build the project using `mvn clean install` command.
3. Paste the build artifact to <IS_HOMe>/repository/components/dropins/ directory.
4. Restart the server.
5. Enable Ask Password feature from Management console.
6. Test creating SCIM user using below curl
`curl -v -k --user admin:admin --data '{"schemas":[],"name":{"familyName":"john","givenName":"doe"},
"userName":"johndoe","password":"password","emails":[{"primary":true,"value":"john@wso2.com"}]}' 
--header "Content-Type:application/json" https://localhost:9443/scim2/Users`
7. Password reset link will be sent to john@wso2.com despite SCIM  request missing the `"EnterpriseUser":{askPassword:"true"}` property.
