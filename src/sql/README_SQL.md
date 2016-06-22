This folder contains all the SQL queries that create and populate the database for our system.
The file "tables.sql" contains the DDL queries, for creating all the tables, as well as some sequences that increment IDs automatically.
The other files are simple insert queries, mostly automatically generated, to populate each and every table.
Since most of them were automatically generated, don't expect them to follow the restrictions our application should enforce.
For example, you can find deliveries with certain material, but the company responsible for the delivery doesn't even supply that material.