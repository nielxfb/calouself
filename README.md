# CaLouselF

CaLouselF is a Java-based application for managing items, offers, transactions, and wishlists. It provides a user interface for users to log in, register, and manage their items and offers.

## Features

- User authentication (login and registration)
- Item management (upload, edit, delete, view)
- Offer management (create, accept, decline)
- Transaction management (create, view history)
- Wishlist management (add, remove, view)

## Technologies Used

- Java
- JavaFX for the user interface
- MySQL for the database
- JDBC for database connectivity

## Project Structure

- `controller`: Contains the controllers for handling user actions and business logic.
- `model`: Contains the data models representing the entities in the application.
- `view/page`: Contains the JavaFX pages for the user interface.
- `util`: Contains utility classes for database connection and session management.
- `abstraction`: Contains abstract classes and interfaces used in the project.
- `builder`: Contains builder classes for constructing responses.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- MySQL database
- JavaFX 17 or higher
- MySQL Connector/J 8.0.26 or higher

### Setting Up the Database

1. Create a MySQL database named `calouself`.
2. Import the database schema and data from the provided SQL file.

### Running the Application

1. Clone the repository:
   ```sh
   git clone https://github.com/nielxfb/calouself.git
   ```
2. Open the project in IntelliJ IDEA.
3. Configure the database connection in `util/Connect.java` with your MySQL credentials.
4. Build and run the project.

## Usage

### User Authentication

- **Login**: Users can log in using their username and password.
- **Register**: New users can register by providing their details.

### Item Management

- **Upload Item**: Users can upload new items with details like name, size, category, and price.
- **Edit Item**: Users can edit the details of their existing items.
- **Delete Item**: Users can delete their items.
- **View Items**: Users can view all items, approved items, or pending items.

### Offer Management

- **Create Offer**: Users can create offers for items with a specified offer price.
- **Accept Offer**: Users can accept offers made by other users.
- **Decline Offer**: Users can decline offers with a reason.

### Transaction Management

- **Create Transaction**: Users can create transactions for purchased items.
- **View History**: Users can view their transaction history.

### Wishlist Management

- **Add to Wishlist**: Users can add items to their wishlist.
- **Remove from Wishlist**: Users can remove items from their wishlist.
- **View Wishlist**: Users can view their wishlist.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements or bug fixes.