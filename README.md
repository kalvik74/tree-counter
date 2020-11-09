
### **Trees Counter**

1) For running service run command: 
        
        mvn spring-boot:run
        
2) Check local application: 
        
        curl -X GET --header 'Accept: application/json' 'http://localhost:8080/trees?x=1027035.901&y=255287.4498&radius=100' 

3) Also I set up CI/CD(based on GitHub Actions and Heroku) for this service and you can check it here: 

        Swigger UI:
        https://holidu-test.herokuapp.com/swagger-ui.html#!/tree-counter-resource/treesUsingGET

        GET example:
        https://holidu-test.herokuapp.com/trees?x=1027035.901&y=255287.4498&radius=100