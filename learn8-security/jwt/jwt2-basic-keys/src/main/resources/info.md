# JWT Basic
- We are using asymmetric encryption
  - encryption & decryption may be performed on different servers
    - encryption: creating/issuing tokens - is done with private key
    - decryption: verifying tokens - is done with public key
    
  - RSA / ESA algorithms are used for asymmetric encryption
  
  - Note: for asymmetric encryption we use MAC(HMAC) algorithms

- We use symmetric encryption when the authorization server & resource server are different

- we created JWT encoder & decoder beans using RSA keys


# Generating keys
1. Generating Private & Public Keys
   ```shell
    openssl genrsa -out private.key 2048
    openssl rsa -in private.key -pubout -out public.key
   ```
   or
    ```shell
    openssl genpkey -algorithm RSA -out private.key -pkeyopt rsa_keygen_bits:4096
    openssl rsa -pubout -in private.key -out public.key
    ```