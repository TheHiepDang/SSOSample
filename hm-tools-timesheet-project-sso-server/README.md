# README #
USAGE Example:
- Grant type = authorization code
    GET {appRoot}/oauth/authorize?response_type=code&client_id=hmtool-appui&redirect_uri=http://example.com => Response: http://example.com/?code={refresh_code}
    curl hmtool-appui:$371237@{appRoot}/oauth/token -d grant_type=authorization_code -d client_id=hmtool-appui -d redirect_uri=http://example.com -d code={refresh_code}
- Grant type = client credentials//SAMPLE
    Login and 
    GET {appRoot}/app/rest/security/accessToken
    or
    POST {appRoot}/oauth/token?grant_type=client_credentials&scope=write; Authorization: Bearer {Basic decrypted clientID:clientPassword}
    Which basically does the same thing as the GET /accessToken

Login strategy: Just login normally, then get access_token. It will automatically be injected into client cookie and to be injected into every request by hmtools-common-security
Logout strategy: 
for (A) Empty userDetails and check this condition using AOP (Controller advice) or whatever that's to your liking
For (B) Remove access_token from your cookies


SSO Server: OAuth2 - client_credentials flow
Currently there are two possible method of implementation:
1, Customize: Which goes against the nature of OAuth2. (A)
- Clients: represent apps (Time, Expense modules etc.)
- Scopes: Permission.
-> Pros: 
    + No need to rework user module.
-> Cons: 
    + Will need to inject userDetails into security context manually, as credentials will now be giving client info (app info). 
    + Multiple users will have the same access_token as it's client bound.
2, Utilize OAuth2 default implementation: (B)
- Clients: represent users.
- Scopes: Permission.
- Pros: 
    + Spring security context will actually make sense, as credentials will give out user info. 
    + Each user will have a unique access token, changing password will de-activate all the login session automatically (As the access token will be rendered invalid immediately)
- Cons:
    + Need rework user module

CLIENTS ARE SUPPOSED TO COMMUNICATE WITH THIS SERVER MODULE VIA RESOURCE MODULE
