# Share Center

* Prerequisites
	- java 8
	- Intellij lombok plugin

* swagger
	- http://localhost:8081/swagger-ui.html
	

## Youtube
- create refresh token (Manual setup)
	- create OAuth 2.0 web client at https://console.developers.google.com/apis/credentials, 
		- with https://developers.google.com/oauthplayground as authorized redirect uri
		- copy client id and client secret
	- go to https://developers.google.com/oauthplayground
		- Use your own OAuth credentials: paste above OAuth Client ID & secret
		- Select & authorize APIs: Youtube data api v3 (https://www.googleapis.com/auth/youtube.upload)
	- generate Authorization code
	- exchange authorization code for (refresh) tokens
	
- Using a refresh token to get access token (Auto - see GoogleAuthService in this repo)
	- https://developers.google.com/identity/protocols/OAuth2WebServer
