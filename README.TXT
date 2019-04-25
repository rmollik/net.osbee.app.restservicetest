
Welcome to the ECF JaxRS integration test project for OS.bee / OSBP

- To use this project, you first have to follow the installation instructions on https://download.osbee.org/documentation/index.php/OS.bee_Software_Factory_Installation_Neon
- Use  http://download.osbee.org/downloads/p2/osbee/neon/latest   as the link to the Softwarefactory repository.
- Use the included  "osbee.target"
- define a string substituion named "repository" which contains the path to the root of the project without the projects name. ( eg. c:\git if net.osbee.app.restservicetest is located in c:\git )
- after the build has completed, launch the application based on the product definition file in net.osbee.app.restservicetest.product

Attention! You're on the development branch then. The content will change without prior notice.

Once you've created and launched the application, you can access it via 

http://localhost:8081/restservicetest/

	Username: Admin
	Password: Admin
	
	