# ss-cloud-authorization-server
My first cloud authorization server implementation


/**
 * redirection URL is responsible to send authorization code on this url
 * http://localhost:8081
 * http://localhost:8080/oauth/authorize?response_type=code&client_id=client2&scope=read
 * response_type=code means get the code in redirect URL for client_id = client2
 * the above url will redirect you to Spring security loginform
 * where user will end username and password after submit we will get approve or reject screen
 * after approval you will get below redirection url
 * http://localhost:8081/?code=BFJsfm

 access 
 http://localhost:8080/oauth/token?grant_type=authorization_code&code=<code from the previous output>&scope=read

use basic auth for client for which we got the code and get oauth token
 * 
 */