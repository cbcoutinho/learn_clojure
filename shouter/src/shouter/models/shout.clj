(ns shouter.models.shout
  (:require [clojure.java.jdbc :as sql]))

; Database connection is either environmental variable, or localhost
; at the port and database name listed here
(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/shouter"))

; Lists all rows of table `shouts` sorted
(defn all []
  (into [] (sql/query spec ["select * from shouts order by id desc"])))

(defn create [shout]
  (sql/insert! spec :shouts [:body] [shout]))

