1. in httpBasic authentication, we need to send username & password in request header in every request.  

2. If we are using browser, it'll show popup and ask for username & password.  
  and the browser will send username & password in request Authorization.  

3. Note:- 
   1. username & password should be encoded to base64 string in ASCII format.  
     in the format "username:password"  
   2. the header value should be prefixed with 'Basic <space>'  
      i.e Authorization: Basic base64Format(username:password)  
      i.e Authorization: Basic dXNlcjpwYXNzd29yZA==  

4. Curl will handle converting to base64 automatically.  
   eg: curl -u user:password http://localhost:8080/
    or  
   eg: curl -H "Authorization: Basic dXNlcjpwYXNzd29yZA==" http://localhost:8080/

5. Spring security won't provide any login / logout pages (bcz every request requires authentication).