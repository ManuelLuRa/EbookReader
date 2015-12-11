# EbookReader
Sample application that uses a DropBox account as ebook library. 

Clean Architecture is implemented at this project. The structure is divided into 4 layers: Presentation, Domain, Repository and Data. Also "Executor" and "Dependencies" folder are available for other purposes.

MVP pattern is used to implements the presentation layer.

Thread management is developed with ThreadPoolExecutor.

Data sources are DropBox Api, for network, and SQLite database, as system cache.

Libraries such as Dagger2 (DI), Butterknife(DI), ormlite(DB management) or Transformer(Object Mapping) are used.

From now, the application is not running. Some problems are detected:

  1. Auth token is not been restablished to DropBox Api object after the first application startup, so DropBox datasource is giving a exception.
  2. When token is OK (At first startup) DropBox datasource is giving a "Bad Request" message, making an Exception. I don't know why it is happen. I think DropBox Api object is Ok. I'm not sure if used path is correct.

Future improvements:
1. Solve bugs
2. Start an "DetailEBookActivity" when an EBook object is selected. The proccess is nearly developed.
3. Allows user to sort by name or date. 
4. Change actual View (ListView) to GridView, in order to allow the change between views.


