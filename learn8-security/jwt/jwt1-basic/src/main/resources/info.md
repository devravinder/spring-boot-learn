# JWT Basic
- We are using symmetric encryption
    - encryption & decryption can be done using only secret
       - encryption: creating/issuing tokens
       - decryption: verifying tokens
       - 
    - MAC(HMAC) algorithms are used for symmetric encryption
  
    - Note: for asymmetric encryption we use RSA / ESA algorithms

- We use symmetric encryption when the same server is authorization & resource server
  

- we created JWT encoder & decoder beans using secret


- Note:- 
  - we can use multiple authentications at the same time
     - username & password, JWT