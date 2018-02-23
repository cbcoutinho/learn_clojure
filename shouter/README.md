# shouter

This clojure web app is essentially a message board with a database
backend using PostgreSQL. It assumes that PostgreSQL is running on
`localhost` and that there exists a `shouter` database. The `shouter`
database can have an existing `shouts` table. If the table doesn't
exist than one will be created.

The tutorial was originally written by someone trying to put a web
service on the Heroku platform

https://devcenter.heroku.com/articles/clojure-web-application
