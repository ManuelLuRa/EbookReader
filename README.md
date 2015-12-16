# EbookReader
Sample application that uses a DropBox account as an ebook library. 

Clean Architecture is implemented on this project. The structure is divided into 4 layers: Presentation, Domain, Repository and Data. Also "Executor" and "Dependencies" folders are available for other purposes.

MVP pattern is used to implements the presentation layer.

Thread management is developed with ThreadPoolExecutor.

Data sources are DropBox Api, for network, and SQLite database, as system cache.

Libraries such as Dagger2 (DI), Butterknife(DI), ormlite(DB management) or Transformer(Object Mapping) are used.

From now, the application is not running. Some problems have been detected:

  1. Auth token was not re-established to DropBox Api object after the application's first startup, making DropBox datasource give an exception.
  2. When token is OK (on first startup) DropBox datasource sends a "Bad Request" message and creates an Exception when I ask for metadata. I don't know why this happens. I think DropBox Api object is Ok. I'm not sure if the path I used is correct.

Future improvements:
  1. Solve bugs
  2. Start a "DetailEBookActivity" when an EBook object is selected. The process is nearly developed.
  3. Allow users to sort by name or date. 
  4. Allow change current View (ListView) to GridView, allowing to switch between views.


