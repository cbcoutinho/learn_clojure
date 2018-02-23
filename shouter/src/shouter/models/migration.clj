(ns shouter.models.migration
  (:require [clojure.java.jdbc :as sql]
            [shouter.models.shout :as shout]))

; Checks to see if `shouts` table exists in database
(defn migrated? []
  (-> (sql/query shout/spec
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='shouts'")])
      first :count pos?))

; Creates a table called `shouts` with following schema if doesn't
; exist
(defn migrate []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (sql/db-do-commands shout/spec
                        (sql/create-table-ddl
                          :shouts
                          [[:id :serial "PRIMARY KEY"]
                           [:body :varchar "NOT NULL"]
                           [:created_at :timestamp
                            "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]]))
    (println " done")))
