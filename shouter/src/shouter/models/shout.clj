(ns shouter.models.shout
  (:require [clojure.java.jdbc :as sql]))

; Database connection is either environmental variable, or localhost
; at the port and database name listed here
(def pg-db (or (System/getenv "DATABASE_URL")
               {:dbtype "postgresql"
                 :dbname "shouter"
                 :host "localhost"
                 :port "5432"
                 :user "chris"}))

; Lists all rows of table `shouts` sorted
(defn all []
  (into [] (sql/query pg-db ["select * from shouts order by id desc"])))

(defn create [shout]
  (sql/insert! pg-db :shouts [:body] [shout]))

