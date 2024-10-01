# Assignment 1

Venue hire systems can be complex. In this assignment, you will be asked to design and implement a simple venue hire system. The system will be able to store information about venues and their bookings.

The system you develop is one that the venue hire company will use to manage their venues and bookings. The system will be used by the venue hire company’s staff to:

View details of all the venues,
Create new venues,
View details of all the bookings for a venue,
Create new bookings for a venue,
Add catering, music, and floral services to a booking,
View the invoice for a booking.
Due to the venue hire company’s limited budget (it’s apparently tough being a venue hire company), we will only design for them a terminal-driven interface. This means that the system will be controlled by a menu system that will be displayed on the terminal. The menu will allow the staff member to select from a list of commands. The staff member will be able to select a command by typing its code, and any arguments it expects. Some commands require additional options, which are prompted by the system one at a time. The system will then execute the appropriate action.

# The Commands
Before proceeding with the tasks, read the code provided to you and start to familiarise yourself with the command-line interface.

To run the application in VS Code in an interactive mode, run the Main class using the Maven wrapper through VS Code’s built-in terminal. If the terminal window is not open, you can open it by clicking on the Terminal menu, then New Terminal. Then type the following command:

./mvnw clean compile exec:java@run for Unix/Mac OS or .\mvnw.cmd clean compile exec:java@run for Windows

To run test cases:

./mvnw clean compile test for Unix/Mac OS or .\mvnw.cmd clean compile test for Windows
