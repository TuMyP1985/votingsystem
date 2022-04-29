**Task for diplom:**

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users

Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)

Menu changes each day (admins do the updates)

Users can vote on which restaurant they want to have lunch at

Only one vote counted per user

If user votes again the same day:
  - If it is before 11:00 we assume that he changed his mind.
  - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.
(https://github.com/JavaWebinar/topjava/blob/doc/doc/graduation.md)

Description:

If user have not role admin, then we hiddet button "Users", and he can't add/update Dishes/Restaurant.

User cannot vote again, if time is after 11:00, but can if he did't vote today.

P.S.:
In this project are any files not for diplom:
- initDB.sql, 
- postgres.properties,
- spring-db.xml (beans for postgres), 
- pom.xml (dependency for postgres).

Please don't look it. It's only for my experience in future, not for Diplom.





