# Accessing-a-DB-through-an-EJB-layer
Java EE, Wildfly, Hibernate, Statefull and Stateless Session bean, Entity, Persistency

# Introduction:

The project consists in writing two different main clients, one for manage user operation and the
other one for administrator operations and a site in order to handle them.
The initial requirements for this project were to develop four different clients, but I preferred to
merge these for simplify the usage of the applications, keeping in any case all the functionalities
required.
The project essentially reproduces a Library management system used to buy books.
In particular the user through the UserClient can:

- register itself on the database;
- log into the system;
- visualize all the book available in the library
- get a cart;
- add one or more books to the user cart;
- visualize the cart and clear it;
- buy the books present in the cart;

Note that an user can run the cart operations even if is not logged into the system, but he/she cannot
buy the content cart before the log in.
AdministratiorClient contains instead admin operations, that consist in:

- adding new books on the database;
- visualize the buying operation (which books has been sold and who is the customer).

Furthermore the instructions for this assignment specify that, the project must use stateful, stateless
beans and entities.
I handle this request saving Account (username,password) and Book (title,price,buyer) informations
in a database, with the help of EJB and two Entity class(Accont and Book). Account and book
information are looked up in stateless EJBs, while the cart is looked up in a stateful EJB.
The motivations of these choice will be explained in the “Explanation” chapter.

For the whole report pleas open report.pdf
