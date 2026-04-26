🚆 Railway Reservation System (Java Swing)

---

📌 Project Overview

This is a Java-based Railway Reservation System built using Swing (GUI).
It simulates core features of an online train booking platform similar to IRCTC.

The system allows users to:

- Login securely
- Search trains between stations
- View available trains
- Check seat availability
- View coach layouts
- Book tickets and generate PNR

---

🛠️ Technologies Used

- Java (Core + OOP)
- Swing (GUI)
- AWT (Graphics & Events)
- Collections Framework (ArrayList, HashMap)
- JDBC (Database Connectivity)

---

✨ Features

🔐 Login System

- Simple authentication system
- Default credentials:
  - Username: "admin"
  - Password: "1234"

---

🚆 Train Management

- Large dataset of trains across India
- Routes include:
  - Bhubaneswar ↔ Puri
  - Mumbai ↔ Delhi
  - Chennai ↔ Bangalore
  - Kolkata ↔ Patna
  - And many more...

---

🔍 Train Search

- Search trains by:
  - Source station
  - Destination station
- Displays matching trains with:
  - Train Number
  - Name
  - Timing
  - Fare

---

💺 Seat Availability

- Multiple coach types:
  - 1AC
  - 2AC
  - 3AC
  - Sleeper
  - Chair Car
- Dynamic seat availability using HashMap

---

🗺️ Seat Layout UI

- Visual seat arrangement
- Seat types:
  - Lower (LB)
  - Middle (MB)
  - Upper (UB)
  - Side Lower (SL)
  - Side Upper (SU)

---

🎟️ Ticket Booking

- Generates unique PNR number
- Shows booking confirmation
- Displays ticket details

---

💰 Pricing System

- Predefined pricing for each coach type
- Managed using HashMap

---

🗄️ Database Connection

- JDBC integration supported
- Connection tested at startup:
  Connection con = DBConnection.getConnection();

---

📂 Project Structure

Railway.java       → Main application file  
DBConnection.java → Database connection logic  

---

▶️ How to Run

1. Open project in any Java IDE (IntelliJ / Eclipse / NetBeans)
2. Ensure JDK is installed
3. Add database connector (if using DB)
4. Run:
   Railway.java
5. Login using:
   Username: admin
Password: 1234

---

🚀 Future Improvements

- Online payment integration
- Real-time seat booking
- User registration system
- Admin dashboard
- Ticket cancellation & refund
- Database-driven train data


🙌 Acknowledgement

Inspired by Indian Railway booking systems and designed for learning Java GUI and system design.

---

👨‍💻 Author

Soumya
