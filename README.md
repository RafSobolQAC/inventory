# Inventory Management System

A simple inventory management system with customer, item, & order functionality.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

```
JDK 
Maven
A MySQL server (local and/or remote for testing & actual usage purposes)
```

Optionally:
```
SonarQube
Nexus
Jenkins
```

### Installing


1) Install JDK on your computer, together with a chosen IDE.

```
Eclipse 4.14.0 was used for development of this software. If you wish to work on it, it is recommended that you use it.
```

2) Install Maven.
3) Install a MySQL server.
```
For the purposes of testing, you should set-up a localhost database.
For this, in your MySQL server, create a database based on the schema sql-schema.sql, found in src/main/resources.

For running the program, it would be a good idea to set up a cloud-based MySQL server.

```

Optional steps (for continuous integration):
4) Install SonarQube.
```
Run it on a VM (perhaps a cloud-based one?) or localhost. It will drastically simplify keeping your code quality up.
```
5) Install Nexus.
```
A bonus step - allows for storing your artifacts away.
```
6) Install Jenkins.
```
Here, the advantages of running it on a cloud-based VM are clear - you want to make sure your code is looked at whenever you push it up to Github. Jenkins makes testing your code, checking its quality, & building it a breeze.

```


When you run the program, it will default to the author's own database - ensure you change it in the Runner-main method (in the call to the IMS instance) to refer to your database.

## Running the tests

To run the tests:
```
mvn test
```
To run IT tests, you will have to
```
mvn clean integration-test
```

### Unit Tests 

Unit tests run on each individual class to make sure it works as expected. Mockito is used to stub other classes for those that are reliant on them.

```
	//This test mocks the creation of a customer in a MysqlCustomerDao class, and thus unit tests the customerService class.
	@Test
	public void createTest() {
		Mockito.when(custDaoMock.create(Mockito.any(Customer.class))).thenReturn(customer);
		assertEquals(customer,customerService.create(customer));
	}

	
```

### Integration Tests 
The integration tests are used to ensure the system works as expected when all connected.
```
	//This test asserts that MysqlCustomerDao class connects fine to the database.
	@Test
	public void customerDaoAddTest() {
		customer.setName("One!");
		Customer otherCustomer = new Customer("One!");
		custDao.create(customer);
		assertEquals(otherCustomer.getName(), custDao.readLatest().getName());
	}

```


## Deployment

The simplest way to deploy this is to use Jenkins. 

Install Jenkins on a cloud VM. Let it poll SCM (your fork of this repo, perhaps?) every minute, or however frequently you like; then, make it run a Maven script of testing it, sending it to SonarQube, and then finally deploying it to an artifact repository. 

To build locally: terminal in the project's main folder:

```
mvn install
cd target/
```
Then to run: 
```
java -jar Inventory.jar
```



## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc